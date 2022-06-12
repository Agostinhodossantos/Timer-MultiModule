package com.netsoft.android.timer.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.netsoft.android.timer.R
import com.netsoft.android.timer.broadcast.NotificationReceiver
import com.hubstaff.utils.common.*
import com.hubstaff.utils.extensions.getOpenTimerTabIntent
import com.hubstaff.utils.extensions.isOreoPlus
import com.hubstaff.utils.extensions.toHhMmSs
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class FinishedTimerService : Service() {

    private val bus = EventBus.getDefault()

    override fun onCreate() {
        super.onCreate()
        bus.register(this)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startForeground(TIMER_FINISH_RUNNING_ID, notification(ZERO_STRING))
        return START_NOT_STICKY
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(state: TimerState.Running) {
        if (state.tick % ONE_THOUSAND_INT == ZERO_LONG) {
            val notification: Notification = notification(state.tick.toHhMmSs())
            val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.notify(TIMER_FINISH_RUNNING_ID, notification)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: FinishedTimerStopService) {
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

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun notification(formattedTick: String): Notification {
        val channelId = "finished_alarm_timer"
        val label = getString(R.string.app_name)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (isOreoPlus()) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            NotificationChannel(channelId, label, importance).apply {
                setSound(null, null)
                notificationManager.createNotificationChannel(this)
            }
        }
        val broadcastIntent = Intent(application, NotificationReceiver::class.java)
        val broadcastPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                application,
                0,
                broadcastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(formattedTick)
            .setContentText(getString(R.string.label_time_up))
            .setSmallIcon(R.drawable.ic_logo)
            .setContentIntent(this.getOpenTimerTabIntent())
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(null)
            .setOngoing(true)
            .setAutoCancel(true)
            .setChannelId(channelId)
            .addAction(R.drawable.ic_logo, getString(R.string.label_stop), broadcastPendingIntent)
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        return builder.build()
    }
}

fun Context.startFinishedTimerService() {
    if (isOreoPlus()) {
        startForegroundService(Intent(this, FinishedTimerService::class.java))
    } else {
        startService(Intent(this, FinishedTimerService::class.java))
    }
}

fun Context.stopFinishedTimerService() {
    EventBus.getDefault().post(FinishedTimerStopService)
    stopService(Intent(this, FinishedTimerService::class.java))
}

object FinishedTimerStopService
