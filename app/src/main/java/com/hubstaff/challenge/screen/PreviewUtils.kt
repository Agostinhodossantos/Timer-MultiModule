package com.hubstaff.challenge.screen


import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.hubstaff.theme.HubstaffAppTheme

@Composable
internal fun ThemedPreview(
    content: @Composable () -> Unit
) {
    HubstaffAppTheme() {
        Surface {
            content()
        }
    }
}
