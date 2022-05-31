package com.hubstaff.challenge.screen.login

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hubstaff.theme.HubstaffAppTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {
            HubstaffAppTheme {
                LoginScreen()
            }
        }

        composeTestRule.onNodeWithText("Register").performClick()
        // TODO cover with ui test

    }
}