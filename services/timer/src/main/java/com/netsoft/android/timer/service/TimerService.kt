package com.netsoft.android.timer.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.netsoft.android.timer.common.TIMER_RUNNING_ID
import com.netsoft.android.timer.extensions.isOreoPlus
import org.greenrobot.eventbus.EventBus

class TimerService : Service() {

    private val bus = EventBus.getDefault()

    override fun onCreate() {
        super.onCreate()
        bus.register(this)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        //startForeground(TIMER_RUNNING_ID, notification(""))
        return START_NOT_STICKY
    }

    fun Context.startTimerService() {
        if (isOreoPlus()) {
            startForegroundService(Intent(this, TimerService::class.java))
        } else {
            startService(Intent(this, TimerService::class.java))
        }
    }

    fun Context.stopTimerService() {
        EventBus.getDefault().post(TimerStopService)
        stopService(Intent(this, TimerService::class.java))
    }

    object TimerStopService

}