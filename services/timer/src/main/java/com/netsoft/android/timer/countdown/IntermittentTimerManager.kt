package com.netsoft.android.timer.countdown

import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface IntermittentTimerManager {
    fun startIntermittentTimer(): Flow<Boolean>
}
