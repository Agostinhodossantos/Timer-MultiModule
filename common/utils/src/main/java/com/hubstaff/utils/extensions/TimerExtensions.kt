package  com.hubstaff.utils.extensions

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import com.hubstaff.utils.common.*
import java.math.BigDecimal
import java.util.concurrent.TimeUnit
import kotlin.math.absoluteValue


fun String.fillWithZeros() = this.padStart(MAX_LENGTH_TIMER, ZERO_STRING.first())
fun String.removeLast() = if (isNotEmpty()) this.take(this.length - 1) else this
fun String.firstInputIsZero(input: String) = this.isEmpty() && input == ZERO_STRING
fun Long.isNotZero(): Boolean = this != ZERO_LONG
fun Long?.getPositiveValue(): Long = this?.let { if (this < 0) ZERO_LONG else this } ?: ZERO_LONG
fun Long.isZero(): Boolean = this == ZERO_LONG
fun Int.isZero(): Boolean = this == ZERO_INT

fun String.toMillis(): Long {
    var timeInMillis = 0L
    this.fillWithZeros().chunked(2).fastForEachIndexed { i, s ->
        when (i) {
            0 -> timeInMillis += TimeUnit.HOURS.toMillis(s.toLong())
            1 -> timeInMillis += TimeUnit.MINUTES.toMillis(s.toLong())
            2 -> timeInMillis += TimeUnit.SECONDS.toMillis(s.toLong())
        }
    }
    return timeInMillis
}

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

fun String.calculateFontSize(): TextUnit {
    return when (length) {
        8 -> 40.sp
        7 -> 48.sp
        5 -> 64.sp
        else -> 72.sp
    }
}

fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun Context.getLaunchIntent() =
    packageManager.getLaunchIntentForPackage("com.hubstaff.challenge")

@SuppressLint("UnspecifiedImmutableFlag")
fun Context.getOpenTimerTabIntent(): PendingIntent {
    val launchIntent = Intent(Intent.ACTION_MAIN)
    launchIntent.addCategory(Intent.CATEGORY_LAUNCHER)
    val cn = ComponentName(
        "com.hubstaff.challenge",
        "com.hubstaff.challenge.screen.main.MainActivity"
    )
    launchIntent.component = cn;
    val intent = getLaunchIntent() ?: launchIntent
    return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}
