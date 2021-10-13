package com.netsoft.android.authentication

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * this is dummy implementation of Auth manager, which fails every first login attempt and then succeed.
 * It lets simulate login after every app launch.
 */
internal class DummyAuthManager @Inject constructor() : AuthenticationManager {
    private val LOGIN_DELAY = 500L
    private var attemptsCounter = 0

    private val _loginState = MutableStateFlow(false)

    override val loginState: StateFlow<Boolean>
        get() = _loginState


    override suspend fun login(email: String, password: String): LoginResult {
        delay(LOGIN_DELAY)
        attemptsCounter++
        return if (attemptsCounter == 1) {
            _loginState.value = false
            LoginResult(false, "next attempt will succeed, try again please")
        } else {
            _loginState.value = true
            LoginResult(true)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthManagerProvider {

    @Singleton
    @Binds
    internal abstract fun getAuthManger(
        impl: DummyAuthManager,
    ): AuthenticationManager
}
