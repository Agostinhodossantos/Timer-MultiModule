package com.netsoft.android.timer.common

object DataManager {
    val columns by lazy { listOf(firstColumn, secondColumn, thirdColumn) }
    val timeUnits by lazy { listOf("h", "m", "s") }
    private val firstColumn by lazy { listOf("1", "4", "7") }
    private val secondColumn by lazy { listOf("2", "5", "8", "0") }
    private val thirdColumn by lazy { listOf("3", "6", "9") }
}
