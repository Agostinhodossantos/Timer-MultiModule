package com.hubstaff.challenge.screen.add

import androidx.lifecycle.ViewModel
import com.netsoft.android.timer.common.DataManager
import com.netsoft.android.timer.common.PreferenceManager
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
