package com.hubstaff.challenge.screen.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

import com.hubstaff.challenge.screen.navigation.ControllerNavApp
import com.hubstaff.theme.HubstaffTheme
import com.hubstaff.theme.LocalSysUiController
import com.hubstaff.theme.SystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = remember { SystemUiController(window) }
            CompositionLocalProvider(LocalSysUiController provides systemUiController) {
                ControllerNavApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HubstaffTheme {
        ControllerNavApp()
    }
}
