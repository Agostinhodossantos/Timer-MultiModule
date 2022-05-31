package com.netsoft.android.timer

import kotlinx.coroutines.flow.Flow

/**
 * interface for ticker, provides the ways to start/stop ticking and subscribe for updates
 */
sealed interface TimeTicker {
    /**
     * shares current timer state
     */
    val timerState: Flow<TimerState>


    /**
     * starts ticker with period
     * @return true if started and false if can't start, because ticker is already running
     */
    fun start(period: Long): Boolean

    /**
     * stops ticker if it is running, else does nothing
     */
    fun stop()
}

sealed class TimerState {
    data class Start(val duration: Long) : TimerState()
    object Started : TimerState()
    data class Running(val duration: Long, val tick: Long) : TimerState()
    object Stopped : TimerState()
    object Paused : TimerState()
    object Finish : TimerState()
    object Finished : TimerState()
}
