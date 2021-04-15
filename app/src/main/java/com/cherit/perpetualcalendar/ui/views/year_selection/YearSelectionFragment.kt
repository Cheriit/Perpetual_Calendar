package com.cherit.perpetualcalendar.ui.views.year_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cherit.perpetualcalendar.R
import com.cherit.perpetualcalendar.ui.components.event_list.EventListRecyclerViewAdapter
import com.cherit.perpetualcalendar.utils.getMovingEvents
import com.google.android.material.bottomnavigation.BottomNavigationView

class YearSelectionFragment : Fragment() {

    private lateinit var yearSelectionViewModel: YearSelectionViewModel
    private lateinit var bottomNavigation: BottomNavigationView

    private lateinit var eventsListView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        yearSelectionViewModel =
            ViewModelProvider(requireActivity()).get(YearSelectionViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_year_selection, container, false)
        eventsListView = root.findViewById(R.id.events_list)
        bottomNavigation = (activity?.findViewById(R.id.nav_view)) as BottomNavigationView
        initNumberPicker(root)
        setViewModelObservers()
        return root
    }

    private fun initNumberPicker(root: View) {
        val numberPicker: NumberPicker = root.findViewById(R.id.year_picker)
        numberPicker.minValue = 1900
        numberPicker.maxValue = 2200
        numberPicker.wrapSelectorWheel = true
        numberPicker.value = yearSelectionViewModel.selectedYear.value!!
        numberPicker.setOnValueChangedListener { _, _, newVal ->
            yearSelectionViewModel.selectedYear.value = newVal
            bottomNavigation.menu.findItem(R.id.navigation_shopping_sunday_list).isVisible = newVal >= 2020
        }
    }

    private fun setViewModelObservers() {
        yearSelectionViewModel.selectedYear.observe(viewLifecycleOwner, {
            eventsListView.adapter = EventListRecyclerViewAdapter(getMovingEvents(it))
        })
    }
}