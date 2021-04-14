package com.cherit.perpetualcalendar.ui.views.year_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cherit.perpetualcalendar.R
import com.cherit.perpetualcalendar.models.Event
import com.cherit.perpetualcalendar.ui.components.event_list.EventListRecyclerViewAdapter
import java.util.*

class YearSelectionFragment : Fragment() {

    private lateinit var yearSelectionViewModel: YearSelectionViewModel

    private lateinit var eventsListView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        yearSelectionViewModel =
                ViewModelProvider(this).get(YearSelectionViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_year_selection, container, false)
        eventsListView = root.findViewById(R.id.events_list)
        setupNumberPicker(root)
        setViewModelObservers()
        return root
    }

    private fun setupNumberPicker(root: View) {
        val numberPicker: NumberPicker = root.findViewById(R.id.year_picker)
        numberPicker.minValue = 1900
        numberPicker.maxValue = 2200
        numberPicker.wrapSelectorWheel = true
        numberPicker.value = yearSelectionViewModel.selected_year.value!!
        numberPicker.setOnValueChangedListener { _, _, newVal ->
            yearSelectionViewModel.selected_year.setValue(newVal)
        }
    }

    private fun setViewModelObservers() {
        yearSelectionViewModel.selected_year.observe(viewLifecycleOwner, Observer {
            eventsListView.adapter = EventListRecyclerViewAdapter(yearSelectionViewModel.movingEvents.value!!)
        })
    }
}