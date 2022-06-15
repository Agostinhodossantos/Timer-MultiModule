
package  com.hubstaff.challenge.screen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hubstaff.challenge.R
import com.hubstaff.theme.HubstaffTheme


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StartButton(visible: Boolean, modifier: Modifier, onClick: () -> Unit) {
    BoxWithConstraints(contentAlignment = Alignment.BottomCenter, modifier = modifier) {
        val height = with(LocalDensity.current) { maxHeight.toPx().toInt() }
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(initialOffsetY = { height }),
            exit = slideOutVertically(targetOffsetY = { height })
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = stringResource(R.string.label_start),
                    tint = HubstaffTheme.colors.textVariantColor
                )
            }
        }
    }
}
