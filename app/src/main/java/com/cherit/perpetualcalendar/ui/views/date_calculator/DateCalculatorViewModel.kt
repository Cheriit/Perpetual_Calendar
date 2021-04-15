package com.cherit.perpetualcalendar.ui.views.date_calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class DateCalculatorViewModel : ViewModel() {
    var dateFrom: MutableLiveData<LocalDate> = MutableLiveData(LocalDate.now())
    var dateTo: MutableLiveData<LocalDate> = MutableLiveData(LocalDate.now())
    var dateDifference: MutableLiveData<Int> = MutableLiveData(0)
    var dateDifferenceFull: MutableLiveData<Int> = MutableLiveData(0)
}