package com.hubstaff.utils.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class TimerExtensionsTest {

    private val timeLongMilliseconds = 36610000L //101010 HhMmSs in Milliseconds
    private val timeString = "101010" //HhMmSs
    private val timeStringFormatted = "10:10:10" //HhMmSs
    private val minute = 10;
    private val timeMmSs = 1010;

    /**
     * string and format correct
     * return success
     */
    @Test
    fun `string_toMillis, when string and format are valid then return success`() {
        assertEquals(
            timeLongMilliseconds,
            timeString.toMillis()
        )
    }

    @Test
    fun `long_toHhMmSs, when format is correct then return string`() {
        assertEquals(
            timeStringFormatted,
            timeLongMilliseconds.toHhMmSs()
        )
    }

    @Test
    fun `int_toFormattedString, when format is correct then return string`() {
        assertEquals(
            "10",
            minute.toFormattedString()
        )
    }

    @Test
    fun `int_minuteToString, when format is correct then return string`() {

        assertEquals(
            "1010",
            timeMmSs.minuteToString(true)
        )

        assertEquals(
            "10",
            minute.minuteToString(false)
        )

    }

}