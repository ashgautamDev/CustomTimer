package com.ashish.custometimer.ui.add

import android.app.TimePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class AddViewModel : ViewModel() {

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        _showDialog.value = false
collectTime()
    }

    private fun collectTime() {

    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    private val _time = MutableLiveData("")
    var time: LiveData<String> = _time

    fun selectDateTime(context: Context) {
        var time = ""
        val currentDateTime = Calendar.getInstance()
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)
        val startSecond = currentDateTime.get(Calendar.SECOND)

        TimePickerDialog(context, { _, hour, minute ->
            val pickedTime = Calendar.getInstance()
            pickedTime.set(hour, minute)
            time = "$hour:$minute"
            updateDateTime(time)
        }, startHour, startMinute, false).show()

    }


    private fun updateDateTime(time: String) {
        _time.value = time
    }

}