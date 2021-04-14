package com.cherit.perpetualcalendar.ui.views.year_selection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cherit.perpetualcalendar.models.Event
import com.cherit.perpetualcalendar.utils.getMovingEvents
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class YearSelectionViewModel : ViewModel() {

    var selectedYear: MutableLiveData<Int> = MutableLiveData<Int>(LocalDate.now().year)

    var movingEvents: MutableLiveData<ArrayList<Event>> = MutableLiveData<ArrayList<Event>>()

}