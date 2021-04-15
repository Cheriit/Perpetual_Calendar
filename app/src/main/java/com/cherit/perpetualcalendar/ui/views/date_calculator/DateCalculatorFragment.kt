package com.cherit.perpetualcalendar.ui.views.date_calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cherit.perpetualcalendar.R
import com.cherit.perpetualcalendar.utils.getDateDifferential
import java.time.LocalDate

class DateCalculatorFragment : Fragment() {

    private lateinit var dateCalculatorViewModel: DateCalculatorViewModel

    private lateinit var fromDatePicker: DatePicker
    private lateinit var toDatePicker: DatePicker
    private lateinit var errorTextView: TextView
    private lateinit var resultsTextView: TextView
    private lateinit var resultsFullTextView: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dateCalculatorViewModel =
                ViewModelProvider(this).get(DateCalculatorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_date_calculator, container, false)

        initViews(root)
        setViewModelObservers()
        setViewsListeners()

        return root
    }

    private fun initViews(root: View) {
        fromDatePicker = root.findViewById(R.id.date_from)
        toDatePicker = root.findViewById(R.id.date_to)
        errorTextView = root.findViewById(R.id.date_calculator_errors)
        resultsTextView = root.findViewById(R.id.date_calculator_results)
        resultsFullTextView = root.findViewById(R.id.date_calculator_results_full)
    }

    private fun setViewsListeners() {
        fromDatePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            dateCalculatorViewModel.dateFrom.value = LocalDate.of(year, monthOfYear+1, dayOfMonth)
        }
        toDatePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            dateCalculatorViewModel.dateTo.value = LocalDate.of(year, monthOfYear+1, dayOfMonth)
        }

    }
    private fun setViewModelObservers() {
        dateCalculatorViewModel.dateFrom.observe(viewLifecycleOwner, {
            val dates = getDateDifferential(it, dateCalculatorViewModel.dateTo.value!!)
            dateCalculatorViewModel.dateDifferenceFull.value = dates.first
            dateCalculatorViewModel.dateDifference.value = dates.second
        })

        dateCalculatorViewModel.dateTo.observe(viewLifecycleOwner, {
            val dates = getDateDifferential(dateCalculatorViewModel.dateFrom.value!!, it)
            dateCalculatorViewModel.dateDifferenceFull.value = dates.first
            dateCalculatorViewModel.dateDifference.value = dates.second
        })

        dateCalculatorViewModel.dateDifference.observe(viewLifecycleOwner, { it ->
            if (dateCalculatorViewModel.dateFrom.value!! <= dateCalculatorViewModel.dateTo.value) {
                (getString(R.string.day_diff_equals) + it + getString(R.string.days)).also { resultsFullTextView.text = it }
                errorTextView.text = ""
            } else {
                resultsFullTextView.text = ""
                errorTextView.text = getString(R.string.cant_calculate_date_diff)
            }
        })

        dateCalculatorViewModel.dateDifferenceFull.observe(viewLifecycleOwner, { it ->
            if (dateCalculatorViewModel.dateFrom.value!! <= dateCalculatorViewModel.dateTo.value) {
                (getString(R.string.day_diff_equals_full) + it + getString(R.string.days)).also { resultsTextView.text = it }
            } else {
                resultsTextView.text = ""
            }
        })
    }
}