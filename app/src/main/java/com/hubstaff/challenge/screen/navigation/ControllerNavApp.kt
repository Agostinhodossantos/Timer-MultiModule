
package  com.hubstaff.challenge.screen.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument

import androidx.navigation.compose.rememberNavController
import com.hubstaff.challenge.R
import com.hubstaff.challenge.screen.add.AddScreen
import com.hubstaff.challenge.screen.login.LoginScreen
import com.hubstaff.challenge.screen.navigation.Screen.Main.ARG_AUTO_PLAY
import com.hubstaff.challenge.screen.timer.TimerScreen
import com.hubstaff.theme.HubstaffTheme


@Composable
fun ControllerNavApp() {

        HubstaffTheme {
            Scaffold(
            ) { innerPadding ->
                val modifier = Modifier.padding(innerPadding)
                val navController = rememberNavController()


                NavHost(navController, startDestination = Screen.Main.route) {
                    composable(
                        Screen.Main.route,
                        arguments = listOf(navArgument(ARG_AUTO_PLAY) { type = NavType.BoolType })
                    ) {
                        val autoPlay = it.arguments?.getBoolean(ARG_AUTO_PLAY) ?: false
                        TimerScreen(
                            viewModel = it.hiltNavGraphViewModel(),
                            modifier = modifier,
                            autoPlay = autoPlay,
                            ) {
                            navController.navigate(route = Screen.AddTimer.route) {
                                popUpTo(Screen.Main.route) { inclusive = true }
                            }
                        }
                    }
                    composable(Screen.AddTimer.route) {
                        AddScreen(viewModel = it.hiltNavGraphViewModel(), modifier = modifier,
                            {
                                navController.navigate(route = Screen.Main.route(true)) {
                                    popUpTo(Screen.AddTimer.route) { inclusive = true }
                                }
                            },
                            {
                                navController.navigate(route = Screen.Login.route) {
                                    popUpTo(Screen.AddTimer.route) { inclusive = true }
                                }
                            })
                    }
                    composable(Screen.Login.route) {
                        LoginScreen() {
                            navController.navigate(route = Screen.AddTimer.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
    }


@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primary
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.label_timer),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = stringResource(R.string.label_actions)
            )
        },
        backgroundColor = backgroundColor,
        modifier = modifier,
        elevation = 0.dp
    )
}
