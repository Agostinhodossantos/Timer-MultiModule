package com.netsoft.android.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

sealed interface AuthenticationManager {
    /**
     * current user login status
     */
    val loginState: StateFlow<Boolean>

    /**
     * initiate login attempt with provided credentials
     * @return result of this operation
     */
    suspend fun login(email: String, password: String): LoginResult
}

data class LoginResult(val success: Boolean, val error: String?=null)

