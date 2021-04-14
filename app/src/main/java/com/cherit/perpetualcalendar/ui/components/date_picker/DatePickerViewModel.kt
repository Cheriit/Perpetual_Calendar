package com.cherit.perpetualcalendar.ui.components.DatePicker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class DatePickerViewModel : ViewModel() {

    var date: MutableLiveData<LocalDate> = MutableLiveData<LocalDate>(LocalDate.now())

    var label: MutableLiveData<String> = MutableLiveData("label")
}