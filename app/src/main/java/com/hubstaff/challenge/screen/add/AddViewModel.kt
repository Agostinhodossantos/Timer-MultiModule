package  com.hubstaff.challenge.screen.add

import androidx.lifecycle.ViewModel
import com.hubstaff.utils.common.DataManager
import com.netsoft.android.timer.manager.PreferenceManager

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager,
    private val dataManager: DataManager
) :
    ViewModel() {

    fun setTimer(time: Long) {
        preferenceManager.timeInMillis = time
    }

    fun getColumns() = dataManager.columns

    fun getTimeUnits() = dataManager.timeUnits
}
