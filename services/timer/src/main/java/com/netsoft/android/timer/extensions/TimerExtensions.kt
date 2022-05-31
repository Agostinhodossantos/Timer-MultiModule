package com.netsoft.android.timer.extensions

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.hubstaff.challenge.screen.main.MainActivity

fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun Context.getLaunchIntent() =
    packageManager.getLaunchIntentForPackage("com.ericktijerou.jettimer")

@SuppressLint("UnspecifiedImmutableFlag")
fun Context.getOpenTimerTabIntent(): PendingIntent {
    val intent = getLaunchIntent() ?: Intent(this, MainActivity::class.java)
    return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}