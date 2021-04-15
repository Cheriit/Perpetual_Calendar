package com.cherit.perpetualcalendar.ui.views.shopping_sunday

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cherit.perpetualcalendar.models.Event

class ShoppingSundayViewModel : ViewModel() {

    var shoppingSundays: MutableLiveData<ArrayList<Event>> = MutableLiveData<ArrayList<Event>>()

}