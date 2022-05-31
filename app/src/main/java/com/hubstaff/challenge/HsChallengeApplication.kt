package com.hubstaff.challenge

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner

import androidx.lifecycle.OnLifecycleEvent
import com.netsoft.android.timer.TimerState
import com.ericktijerou.utils.common.PreferenceManager
import com.netsoft.android.timer.service.startFinishedTimerService
import com.netsoft.android.timer.service.startTimerService
import com.netsoft.android.timer.service.stopFinishedTimerService
import com.netsoft.android.timer.service.stopTimerService
import dagger.hilt.android.HiltAndroidApp
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.*
import javax.inject.Inject


@HiltAndroidApp
class HsChallengeApplication : Application(), LifecycleObserver {

    @Inject
    lateinit var preferences: PreferenceManager
    private var timer: Timer? = null

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        EventBus.getDefault().register(this)
        if (!preferences.isTimerRunning) {
            stopTimerService()
        }
    }

    override fun onTerminate() {
        EventBus.getDefault().unregister(this)
        super.onTerminate()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onAppBackgrounded() {
        if (preferences.isTimerRunning) {
            startTimerService()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onAppForegrounded() {
        stopTimerService()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(state: TimerState.Start) {
        preferences.isTimerRunning = true
        val delay = 0L
        val period = 250L
        timer = Timer()
        var interval = state.duration
        timer?.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    interval -= period
                    val newState = TimerState.Running(state.duration, interval)
                    EventBus.getDefault().post(newState)
                    if (interval == 0L) {
                        startFinishedTimerService()
                    } else if (interval < 0) {
                        stopTimerService()
                    }
                }
            },
            delay, period
        )
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(state: TimerState.Finish) {
        preferences.isTimerRunning = false
        stopTimerService()
        stopFinishedTimerService()
        timer?.cancel()
    }
}

