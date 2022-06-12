package com.hubstaff.utils.common

import com.hubstaff.utils.extensions.toMillis
import org.junit.Assert
import org.junit.Test

class TimerConstantsTest {

    @Test
    fun `MAX_LENGTH_TIMER, when MAX_LENGTH is 6 then return success`() {
        Assert.assertEquals(
            6,
            MAX_LENGTH_TIMER
        )
    }

    @Test
    fun `ONE_THOUSAND_INT, when is 1000 then return success`() {
        Assert.assertEquals(
            1000,
            ONE_THOUSAND_INT
        )
    }

    @Test
    fun `TIMER_RUNNING_ID, when is 1000 then return success`() {
        Assert.assertEquals(
            10000,
            TIMER_RUNNING_ID
        )
    }

    @Test
    fun `ONE_MINUTE_MILLIS, when is 1000 then return success`() {
        Assert.assertEquals(
            60000,
            ONE_MINUTE_MILLIS
        )
    }
}