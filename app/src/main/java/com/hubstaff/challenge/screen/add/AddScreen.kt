package  com.hubstaff.challenge.screen.add


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hubstaff.challenge.R
import com.hubstaff.utils.common.DataManager
import com.hubstaff.challenge.screen.component.StartButton
import com.hubstaff.challenge.screen.login.LoginViewModel
import com.hubstaff.challenge.screen.ThemedPreview
import com.hubstaff.utils.common.*
import com.hubstaff.theme.HubstaffTheme
import com.hubstaff.utils.extensions.fillWithZeros
import com.hubstaff.utils.extensions.firstInputIsZero
import com.hubstaff.utils.extensions.removeLast
import com.hubstaff.utils.extensions.toMillis

import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddScreen(viewModel: AddViewModel, modifier: Modifier, navigateToMain: () -> Unit, navigateToLogin: () -> Unit) {
    val (isFinish, setFinish) = remember { mutableStateOf(false) }
    val isLoading by remember { mutableStateOf(false) }
    val loginViewModel: LoginViewModel = hiltViewModel()
    val state =  loginViewModel.state.value

    LaunchedEffect(state) {
        when(state) {
            is LoginViewModel.LoginUiState.Error -> {
                navigateToLogin()
            }
            is LoginViewModel.LoginUiState.Empty -> {

            }
            is LoginViewModel.LoginUiState.Success -> {
            }
            else -> { }
        }
    }

    //TODO Show loading

    BoxWithConstraints {
        val offsetY = with(LocalDensity.current) { maxHeight.toPx().toInt() / 2 }
        AnimatedVisibility(
            visible = !isFinish,
            exit = slideOutVertically(targetOffsetY = { offsetY }) + fadeOut(),
            enter = slideInVertically(initialOffsetY = { offsetY }),
            initiallyVisible = false
        ) {
            AddScreenBody(
                timeUnits = viewModel.getTimeUnits(),
                dialColumns = viewModel.getColumns(),
                modifier = modifier,
                navigateToMain = {
                    viewModel.setTimer(it)
                    setFinish(true)
                }
            )
        }
    }
    LaunchedEffect(isFinish) {
        if (isFinish) {
            delay(100)
            navigateToMain()
        }
    }
}

@Composable
fun AddScreenBody(
    timeUnits: List<String>,
    dialColumns: List<List<String>>,
    modifier: Modifier = Modifier,
    navigateToMain: (Long) -> Unit
) {
    Column(
        modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxSize()
    ) {
        var textState by remember { mutableStateOf(EMPTY) }
        TimerValue(
            value = textState.fillWithZeros(),
            timeUnits = timeUnits,
            enabled = textState.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp, bottom = 36.dp),
            onDelete = { textState = textState.removeLast() },
            onDeleteAll = { textState = EMPTY }
        )
        Spacer(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .fillMaxWidth()
                .background(HubstaffTheme.colors.searchBoxColor)
                .height(1.5.dp)
        )
        Dial(columns = dialColumns) {
            if (textState.length < 6 && !textState.firstInputIsZero(it)) textState += it
        }
        StartButton(
            visible = textState.isNotEmpty(),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) { navigateToMain(textState.toMillis()) }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimerValue(
    value: String,
    enabled: Boolean,
    modifier: Modifier,
    timeUnits: List<String>,
    onDelete: () -> Unit,
    onDeleteAll: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        value.chunked(2).forEachIndexed { index, s ->
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                val color =
                    if (enabled) MaterialTheme.colors.secondaryVariant else HubstaffTheme.colors.textSecondaryColor
                Text(
                    text = s,
                    style = MaterialTheme.typography.h3.copy(
                        fontWeight = FontWeight.W400,
                        letterSpacing = 1.sp
                    ),
                    color = color
                )
                Text(
                    text = timeUnits[index],
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier.padding(bottom = 6.dp),
                    color = color
                )
            }
        }

        Icon(
            imageVector = Icons.Outlined.Backspace,
            contentDescription = stringResource(R.string.label_delete),
            tint = if (enabled) HubstaffTheme.colors.textPrimaryColor else HubstaffTheme.colors.textSecondaryColor,
            modifier = Modifier
                .padding(start = 8.dp)
                .combinedClickable(
                    onClick = onDelete,
                    onLongClick = onDeleteAll,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false, radius = 32.dp)
                )
        )
    }
}

@Composable
fun Dial(columns: List<List<String>>, onItemClick: (String) -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Row {
            columns.forEach {
                Column {
                    it.forEach {
                        DialItem(value = it, onItemClick)
                    }
                }
            }
        }
    }
}

@Composable
fun DialItem(value: String, onItemClick: (String) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(120.dp)
            .height(85.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, radius = 60.dp),
                onClick = { onItemClick(value) }
            )
    ) {
        Text(
            text = value,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.Normal,
            ),
        )
    }
}

@Preview("Add screen body")
@Composable
fun PreviewAddScreenBody() {
    ThemedPreview {
        AddScreenBody(
            timeUnits = DataManager.timeUnits,
            dialColumns = DataManager.columns
        ) {}
    }
}

@Preview("Add screen body dark")
@Composable
fun PreviewAddScreenBodyDark() {
    ThemedPreview {
        AddScreenBody(
            timeUnits = DataManager.timeUnits,
            dialColumns = DataManager.columns
        ) {}
    }
}
