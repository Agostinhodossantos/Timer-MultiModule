package com.hubstaff.challenge.screen.login

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netsoft.android.authentication.repository.ImplAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

@OptIn(InternalCoroutinesApi::class)
@HiltViewModel
class LoginViewModel @Inject constructor(private  val implRepository: ImplAuthManager): ViewModel() {

    var isLoggedIn by mutableStateOf(false)
    var isLoading by mutableStateOf(true)

    init {
        retrieveUser()
    }

    suspend fun signIn(email: String, password: String) {

        implRepository.login(email, password)
        isLoggedIn = true
        isLoading = false
    }

    fun retrieveUser(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                implRepository.getUser().collect {
                    Timber.d("RETURN $it.toString()")
                    withContext(Dispatchers.Main) {
                        isLoggedIn = true
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                Timber.d("RETUR $e")
                isLoggedIn = false
                isLoading = false
            }

        }
    }

}

val UserState = compositionLocalOf<LoginViewModel> { error("LoginViewModel Context Not Found!") }

