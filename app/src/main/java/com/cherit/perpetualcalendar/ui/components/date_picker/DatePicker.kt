package com.cherit.perpetualcalendar.ui.components.date_picker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.cherit.perpetualcalendar.R
import com.cherit.perpetualcalendar.ui.components.DatePicker.DatePickerViewModel
import java.time.DateTimeException
import java.time.LocalDate

class DatePicker : Fragment() {

    companion object {
        fun newInstance() = DatePicker()
    }

    lateinit var viewModel: DatePickerViewModel

    private lateinit var labelTextView: TextView
    private lateinit var day: NumberPicker
    private lateinit var month: NumberPicker
    private lateinit var year: NumberPicker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.date_picker_fragment, container, false)

        initViews(root)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DatePickerViewModel::class.java)
        setViewModelObservers()
        setupPickers()
        setListeners()
    }

    private fun setupPickers() {
        val current_date = LocalDate.now()
        day.minValue = 1
        day.maxValue = 31
        day.value = current_date.dayOfMonth

        month.minValue = 1
        month.maxValue = 12
        month.value = current_date.monthValue
        month.displayedValues = arrayOf(
            "Styczeń", "Luty", "Marzec",
            "Kwiecień", "Maj", "Czerwiec",
            "Lipiec", "Sierpień", "Wrzesień",
            "Październik", "Listopad", "Grudzień")
        year.minValue = 1900
        year.maxValue = 2200
        year.value = current_date.year
    }

    private fun initViews(root: View) {
        labelTextView = root.findViewById(R.id.date_picker_text)
        day = root.findViewById(R.id.date_picker_day)
        month = root.findViewById(R.id.date_picker_month)
        year = root.findViewById(R.id.date_picker_year)
    }

    private fun setViewModelObservers() {

        viewModel.label.observe(viewLifecycleOwner, {
            labelTextView.text = it
        })

        viewModel.date.observe(viewLifecycleOwner, {
            day.value = it.dayOfMonth
            month.value = it.monthValue
            year.value = it.year
        })
    }

    private fun setListeners() {
        day.setOnValueChangedListener { picker, oldVal, newVal ->  updateDate(picker, oldVal, newVal)}
        month.setOnValueChangedListener { picker, oldVal, newVal ->  updateDate(picker, oldVal, newVal)}
        year.setOnValueChangedListener { picker, oldVal, newVal ->  updateDate(picker, oldVal, newVal)}

    }

    private fun updateDate(picker: NumberPicker, oldVal:Int, newVal: Int) {
        var date: LocalDate
        var currentValue: Int = newVal
        try {
            when (picker) {
                day -> date = LocalDate.of(year.value, month.value, newVal)
                month -> date = LocalDate.of(year.value, newVal, day.value)
                year -> date = LocalDate.of(newVal, month.value, day.value)
                else -> date = LocalDate.now()
            }
        } catch (e: DateTimeException) {
            when (picker) {
                day -> date = LocalDate.of(year.value, month.value, oldVal)
                month -> date = LocalDate.of(year.value, oldVal, day.value)
                year -> date = LocalDate.of(oldVal, month.value, day.value)
                else -> date = LocalDate.now()
            }
            currentValue = oldVal
        }
        picker.value = currentValue
        viewModel.date.value = date
        Log.i("DATE", viewModel.date.value.toString())
    }


    fun setLabel(label: String) {
        viewModel.label.value = label
    }

    fun getDate(): MutableLiveData<LocalDate> {
        return viewModel.date
    }
}