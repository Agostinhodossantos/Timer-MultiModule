package com.netsoft.android.timer.extensions

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.hubstaff.challenge.screen.main.MainActivity
import com.netsoft.android.timer.common.ZERO_STRING
import java.math.BigDecimal
import kotlin.math.absoluteValue

fun Float.roundUp(): Long = this.toBigDecimal().setScale(0, BigDecimal.ROUND_UP).longValueExact()

fun Int.toStringOrEmpty(): String = if (this.isZero()) EMPTY else this.toString()
fun Int.toFormattedString(): String {
    return if (absoluteValue in 9 downTo 0) "$ZERO_STRING$absoluteValue" else absoluteValue.toStringOrEmpty()
}

fun Int.minuteToString(hasHour: Boolean): String =
    if (hasHour) this.toFormattedString() else this.toStringOrEmpty()

fun Int.secondToString(hasMinute: Boolean): String =
    if (hasMinute) this.toFormattedString() else this.toString()

fun String.removeExtraColon(): String =
    if (this.first().toString() == COLON) takeLast(length - 1) else this

fun Long.toHhMmSs(): String {
    val hours = ((this / (1000 * 60 * 60) % 24)).toInt().toStringOrEmpty()
    val minutes = ((this / (1000 * 60) % 60)).toInt().minuteToString(hours.isNotEmpty())
    val seconds = ((this / 1000) % 60).toInt().secondToString(minutes.isNotEmpty())
    var formattedTime = "$hours$COLON$minutes$COLON$seconds"
    while (formattedTime.isNotEmpty() && formattedTime.first().toString() == COLON) {
        formattedTime = formattedTime.removeExtraColon()
    }
    return formattedTime
}


fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun Context.getLaunchIntent() =
    packageManager.getLaunchIntentForPackage("com.ericktijerou.jettimer")

@SuppressLint("UnspecifiedImmutableFlag")
fun Context.getOpenTimerTabIntent(): PendingIntent {
    val intent = getLaunchIntent() ?: Intent(this, MainActivity::class.java)
    return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}