package  com.hubstaff.challenge.screen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry

sealed class Screen(val route: String) {
    object Main : Screen("main/{autoplay}") {
        const val ARG_AUTO_PLAY: String = "autoplay"
        fun route(autoplay: Boolean) = "main/$autoplay"
    }

    object AddTimer : Screen("addtimer")
    object Login : Screen("login")
}

@Composable
inline fun <reified VM : ViewModel> NavBackStackEntry.hiltNavGraphViewModel(): VM {
    val viewModelFactory = HiltViewModelFactory(LocalContext.current, this)
    return ViewModelProvider(this, viewModelFactory).get(VM::class.java)
}
