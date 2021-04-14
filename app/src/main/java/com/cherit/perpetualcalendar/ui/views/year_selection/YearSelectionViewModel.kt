package com.cherit.perpetualcalendar.ui.views.year_selection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cherit.perpetualcalendar.models.Event
import com.cherit.perpetualcalendar.utils.getMovingEvents
import java.util.*
import kotlin.collections.ArrayList

class YearSelectionViewModel : ViewModel() {

    private val _selected_year = MutableLiveData<Int>().apply {
        value = Calendar.getInstance().get(Calendar.YEAR)
    }

    var selected_year: MutableLiveData<Int> = _selected_year

    var movingEvents: MutableLiveData<ArrayList<Event>> = MutableLiveData(getMovingEvents(selected_year.value!!))
}