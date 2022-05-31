package com.netsoft.android.timer.countdown

import com.ericktijerou.utils.common.FIVE_HUNDRED
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.util.Timer
import java.util.TimerTask

class IntermittentTimerManagerImpl : IntermittentTimerManager {

    override fun startIntermittentTimer() = callbackFlow<Boolean> {
        val timer = Timer()
        var time = true
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    try {
                        offer(time)
                    } catch (e: Exception) {
                    }
                    time = !time
                }
            },
            0,
            FIVE_HUNDRED
        )
        awaitClose { timer.cancel() }
    }
}
