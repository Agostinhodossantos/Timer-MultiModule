package com.netsoft.android.timer.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.hubstaff.utils.common.*
import com.hubstaff.utils.extensions.getOpenTimerTabIntent
import com.hubstaff.utils.extensions.isOreoPlus
import com.hubstaff.utils.extensions.toHhMmSs
import com.netsoft.android.timer.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TimerService : Service() {

    private val bus = EventBus.getDefault()

    override fun onCreate() {
        super.onCreate()
        bus.register(this)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startForeground(TIMER_RUNNING_ID, notification(""))
        return START_NOT_STICKY
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(state: TimerState.Running) {
        if (state.tick % ONE_THOUSAND_INT == ZERO_LONG) {
            val notification: Notification = notification(state.tick.toHhMmSs())
            val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.notify(TIMER_RUNNING_ID, notification)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: TimerStopService) {
        stopService()
    }

    private fun stopService() {
        if (isOreoPlus()) {
            stopForeground(true)
        } else {
            stopSelf()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bus.unregister(this)
    }

    private fun notification(formattedTick: String): Notification {
        val channelId = "simple_alarm_timer"
        val label = getString(R.string.app_name)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (isOreoPlus()) {
            val importance = NotificationManager.IMPORTANCE_LOW
            NotificationChannel(channelId, label, importance).apply {
                setSound(null, null)
                notificationManager.createNotificationChannel(this)
            }
        }

        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(formattedTick)
            .setContentText(getString(R.string.label_timer))
            .setSmallIcon(R.drawable.ic_logo)
            .setContentIntent(this.getOpenTimerTabIntent())
            .setSound(null)
            .setOngoing(true)
            .setAutoCancel(true)
            .setChannelId(channelId)

        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        return builder.build()
    }
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
