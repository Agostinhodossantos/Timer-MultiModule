package com.hubstaff.challenge.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.hubstaff.challenge.R
import com.hubstaff.theme.HubstaffAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HubstaffAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Main()
                }
            }
        }
    }

    @Composable
    fun Main() {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.main_screen_message),
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HubstaffAppTheme {
            Main()
        }
    }
}
