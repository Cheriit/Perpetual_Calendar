package com.cherit.perpetualcalendar.ui.views.shopping_sunday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cherit.perpetualcalendar.R
import com.cherit.perpetualcalendar.ui.components.event_list.EventListRecyclerViewAdapter
import com.cherit.perpetualcalendar.ui.views.year_selection.YearSelectionViewModel
import com.cherit.perpetualcalendar.utils.getShoppingSundayList

class ShoppingSundayFragment : Fragment() {

    private lateinit var shoppingSundayViewModel: ShoppingSundayViewModel
    private lateinit var yearSelectionViewModel: YearSelectionViewModel
    private lateinit var shoppingSundayList: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        shoppingSundayViewModel =
            ViewModelProvider(this).get(ShoppingSundayViewModel::class.java)

        yearSelectionViewModel =
            ViewModelProvider(requireActivity()).get(YearSelectionViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_shopping_sunday, container, false)
        shoppingSundayList = root.findViewById(R.id.shopping_sunday_list)
        setViewModelListener()
        shoppingSundayViewModel.shoppingSundays.value = getShoppingSundayList(yearSelectionViewModel.selectedYear.value!!)
        return root
    }

    private fun setViewModelListener() {
        shoppingSundayViewModel.shoppingSundays.observe(viewLifecycleOwner, {
            shoppingSundayList.adapter = EventListRecyclerViewAdapter(it)
        })
    }
}