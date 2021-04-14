package com.cherit.perpetualcalendar.ui.views.shopping_sunday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cherit.perpetualcalendar.models.Event
import com.cherit.perpetualcalendar.utils.getShoppingSundayList
import java.util.*
import kotlin.collections.ArrayList

class ShoppingSundayViewModel : ViewModel() {

    var shoppingSundays: MutableLiveData<ArrayList<Event>> = MutableLiveData<ArrayList<Event>>()

}