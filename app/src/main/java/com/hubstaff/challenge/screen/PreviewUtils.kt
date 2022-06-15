package  com.hubstaff.challenge.screen

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.hubstaff.theme.HubstaffTheme

@Composable
internal fun ThemedPreview(
    content: @Composable () -> Unit
) {
    HubstaffTheme {
        Surface {
            content()
        }
    }
}
