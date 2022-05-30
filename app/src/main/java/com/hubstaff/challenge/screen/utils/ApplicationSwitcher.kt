package com.hubstaff.challenge.screen.utils

import androidx.compose.runtime.Composable
import com.hubstaff.challenge.screen.login.LoginScreen

@Composable
fun ApplicationSwitcher() {
   //Todo get login state from ViewModel
    if (false) {
        //Todo TimerScreen()
    } else {
        LoginScreen()
    }
}