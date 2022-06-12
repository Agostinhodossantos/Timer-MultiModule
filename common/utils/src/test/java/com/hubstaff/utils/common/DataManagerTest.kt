package com.hubstaff.utils.common

import org.junit.Assert.*
import org.junit.Test

class DataManagerTest {

    var dtManager = DataManager
    var columnsList = listOf(
        listOf("1", "4", "7"),  //firstColumn
        listOf("2", "5", "8", "0"),  //secondColumn
        listOf("3", "6", "9")) //thirdColumn

    @Test
    fun `timeUnits, should be ordered in hour, minute and seconds(h,m,s)`() {
        assertEquals(
            listOf("h", "m", "s"),
            dtManager.timeUnits
        )
    }

    @Test
    fun `columns, when columns list are valid then return success`() {

        assertEquals(
            columnsList[0],
            dtManager.columns[0]
        )

        assertEquals(
            columnsList[1],
            dtManager.columns[1]
        )

        assertEquals(
            columnsList[2],
            dtManager.columns[2]
        )
    }
}