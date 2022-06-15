package com.hubstaff.challenge.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _state = mutableStateOf<LoginUiState>(LoginUiState.Empty)
    val state: State<LoginUiState> = _state

    suspend fun signIn(email: String, password: String) {
        _state.value = LoginUiState.Loading
        implRepository.login(email, password)
        _state.value = LoginUiState.Success
    }

    init {
        retrieveUser()
    }

    fun retrieveUser(){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                implRepository.getUser().collect {
                    withContext(Dispatchers.Main) {
                        if (it.success) {
                            _state.value = LoginUiState.Success
                        } else {
                            _state.value  = LoginUiState.Error(it.error.toString())
                        }
                    }
                }
            } catch (e: Exception) {
                Timber.d("RETURN $e")
                _state.value  = LoginUiState.Error(e.toString())
            }

        }
    }

    sealed class LoginUiState {
        object Success : LoginUiState()
        data class Error(val message: String) : LoginUiState()
        object Loading : LoginUiState()
        object Empty : LoginUiState()
    }
}


