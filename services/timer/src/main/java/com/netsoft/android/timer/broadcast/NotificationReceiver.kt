package com.netsoft.android.timer.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hubstaff.utils.common.TimerState
import org.greenrobot.eventbus.EventBus

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        EventBus.getDefault().post(TimerState.Finish)
        EventBus.getDefault().post(TimerState.Finished)
    }
}
