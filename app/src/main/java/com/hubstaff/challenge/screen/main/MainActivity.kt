package com.hubstaff.challenge.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.hubstaff.challenge.screen.login.LoginViewModel
import com.hubstaff.challenge.screen.login.UserState
import com.hubstaff.challenge.screen.main.ApplicationSwitcher
import com.hubstaff.theme.HubstaffTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HubstaffTheme {
                CompositionLocalProvider(UserState provides loginViewModel) {
                    ControllerNavApp()
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HubstaffTheme {
        }
    }
}
