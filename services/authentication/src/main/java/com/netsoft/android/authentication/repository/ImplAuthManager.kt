package com.netsoft.android.authentication.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.netsoft.android.authentication.AuthenticationManager
import com.netsoft.android.authentication.LoginResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


const val DataStore_NAME = "USERS"

val Context.datastore : DataStore< Preferences> by  preferencesDataStore(name = DataStore_NAME)


class ImplAuthManager(private val context: Context) : AuthenticationManager {

    companion object{
        val EMAIL = stringPreferencesKey("EMAIL")
        val PASSWORD = stringPreferencesKey("PASSWORD")
    }

    private val _loginState = MutableStateFlow(false)

    override val loginState: StateFlow<Boolean>
        get() = _loginState


     override suspend fun getUser() = context.datastore.data.map { user ->
        LoginResult(user[EMAIL]!!.isNotEmpty(),)
    }

    override suspend fun login(email: String, password: String): LoginResult {
        context.datastore.edit { phonebooks->
            phonebooks[EMAIL] = email
            phonebooks[PASSWORD]= password
        }
        _loginState.value = true
       return LoginResult(true)
    }
}