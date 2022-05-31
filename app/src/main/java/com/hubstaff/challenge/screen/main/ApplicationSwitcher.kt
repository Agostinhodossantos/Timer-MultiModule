package com.hubstaff.challenge.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hubstaff.challenge.screen.login.LoginScreen
import com.hubstaff.challenge.screen.login.UserState
import com.hubstaff.challenge.screen.timer.TimerScreen

@Composable
fun ApplicationSwitcher() {
    val vm = UserState.current

    if (vm.isLoading) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator()
        }
    } else {
        if (vm.isLoggedIn) {
          ///  TimerScreen()

        } else {
            LoginScreen()
        }
    }
}