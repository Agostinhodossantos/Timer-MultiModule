package com.hubstaff.challenge.screen.login

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay


class LoginViewModel : ViewModel() { // TODO Inject repository
    var isLoggedIn by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    suspend fun signIn(email: String, password: String) {
        isLoading = true
        delay(2000)
        isLoggedIn = true
        isLoading = false
    }

}

val UserState = compositionLocalOf<LoginViewModel> { error("LoginViewModel Context Not Found!") }

