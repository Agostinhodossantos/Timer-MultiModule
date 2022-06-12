package  com.hubstaff.utils.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun io(f: suspend () -> Unit) {
    withContext(Dispatchers.IO) { f.invoke() }
}

fun CoroutineScope.launchInIO(f: suspend CoroutineScope.() -> Unit) {
    launch(Dispatchers.IO) {
        f.invoke(this)
    }
}

suspend fun ui(f: suspend () -> Unit) {
    withContext(Dispatchers.Main) { f.invoke() }
}

fun CoroutineScope.launchInUI(f: suspend CoroutineScope.() -> Unit) {
    launch(Dispatchers.Main) {
        f.invoke(this)
    }
}
