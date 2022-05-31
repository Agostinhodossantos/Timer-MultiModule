package com.hubstaff.challenge.screen.timer

import androidx.lifecycle.*
import com.netsoft.android.timer.TimerState
import com.ericktijerou.utils.common.*
import com.netsoft.android.timer.countdown.IntermittentTimerManager
import com.ericktijerou.utils.extensions.getPositiveValue
import com.ericktijerou.utils.extensions.toHhMmSs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject
import kotlinx.coroutines.flow.collect


@HiltViewModel
class TimerViewModel @Inject constructor(
    private val intermittentTimerManager: IntermittentTimerManager,
    private val preferenceManager: PreferenceManager
) :
    ViewModel() {

    init {
        EventBus.getDefault().register(this)
    }

    override fun onCleared() {
        EventBus.getDefault().unregister(this)
        super.onCleared()
    }

    private val _timerLabel = MutableLiveData<String>()
    val timerLabel: LiveData<String> = _timerLabel

    private var remainingTimeInMillis = ZERO_LONG
    private val _timerState = MutableLiveData<TimerState>()
    val timerScreenState: LiveData<TimerState> = _timerState

    private var visibilityJob = SupervisorJob()

    private val _tick = MutableLiveData<Long>()
    val tick: LiveData<Long> = _tick

    private val _timerVisibility = MutableLiveData<Boolean>()
    val timerVisibility = Transformations.switchMap(_timerVisibility) {
        visibilityJob = SupervisorJob()
        liveData(Dispatchers.IO + visibilityJob) {
            if (it) {
                emit(true)
            } else {
                intermittentTimerManager.startIntermittentTimer().collect { tickInSeconds ->
                    emit(tickInSeconds)
                }
            }
        }
    }

    private fun resumeTimer(millisUntilFinished: Long) {
        EventBus.getDefault().post(TimerState.Finish)
        visibilityJob.cancel()
        _timerLabel.value = millisUntilFinished.toHhMmSs()
        _timerVisibility.value = true
        EventBus.getDefault().post(TimerState.Start(millisUntilFinished))
        _timerState.value = TimerState.Started
    }

    fun startTimer() {
        EventBus.getDefault().post(TimerState.Finish)
        visibilityJob.cancel()
        _timerVisibility.value = true
        EventBus.getDefault().post(TimerState.Start(getTempTimer()))
        _timerState.value = TimerState.Started
    }

    private fun pauseTimer() {
        visibilityJob.cancel()
        EventBus.getDefault().post(TimerState.Finish)
        _timerVisibility.value = false
        _timerState.value = TimerState.Paused
    }

    private fun reset() {
        onFinish()
    }

    fun getTimer() = preferenceManager.timeInMillis

    private fun setTempTimer(value: Long) {
        preferenceManager.tempTimeInMillis = value
    }

    fun getTempTimer() = preferenceManager.tempTimeInMillis.coerceAtLeast(getTimer())

    fun clearTimer() {
        visibilityJob.cancel()
        EventBus.getDefault().post(TimerState.Finish)
        preferenceManager.tempTimeInMillis = ZERO_LONG
        preferenceManager.timeInMillis = ZERO_LONG
    }

    fun onActionClick(currentScreenState: TimerState) {
        when (currentScreenState) {
            TimerState.Started -> pauseTimer()
            TimerState.Stopped -> startTimer()
            TimerState.Paused -> resumeTimer(remainingTimeInMillis)
            TimerState.Finished -> reset()
        }
    }

    private fun onFinish() {
        EventBus.getDefault().post(TimerState.Finish)
        onFinishedState()
    }

    private fun onFinishedState() {
        visibilityJob.cancel()
        preferenceManager.tempTimeInMillis = ZERO_LONG
        _timerLabel.value = getTimer().toHhMmSs()
        _timerVisibility.value = true
        _tick.value = getTimer()
        _timerState.value = TimerState.Stopped
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(state: TimerState.Finished) {
        onFinishedState()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(state: TimerState.Running) {
        remainingTimeInMillis = state.tick
        _tick.value = state.tick
        if (state.tick % ONE_THOUSAND_INT == ZERO_LONG) _timerLabel.value = state.tick.toHhMmSs()
        if (state.tick <= ZERO_LONG) {
            if (_timerVisibility.value != false) _timerVisibility.value = false
            if (_timerState.value != TimerState.Finished) _timerState.value = TimerState.Finished
        } else {
            if (_timerState.value != TimerState.Started) _timerState.value = TimerState.Started
        }
    }

    private fun getElapsedTime(currentTime: Long): Long {
        return if (remainingTimeInMillis > ZERO_INT) currentTime - remainingTimeInMillis else ZERO_LONG
    }

    fun onOptionTimerClick(currentScreenState: TimerState) {
        when (currentScreenState) {
            TimerState.Started, TimerState.Finished -> {
                val currentTime = getTempTimer()
                setTempTimer(
                    tick.value.getPositiveValue() + ONE_MINUTE_MILLIS + getElapsedTime(
                        currentTime = currentTime
                    )
                )
                resumeTimer(getTempTimer() - getElapsedTime(currentTime = currentTime))
            }
            else -> reset()
        }
    }
}
