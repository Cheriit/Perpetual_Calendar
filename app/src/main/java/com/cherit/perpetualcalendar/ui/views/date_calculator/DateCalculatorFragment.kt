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

    override fun onStart() {
        super.onStart()
    }

    private fun initViews(root: View) {
        fromDatePicker = root.findViewById(R.id.dateFrom)
        toDatePicker = root.findViewById(R.id.dateTo)
        errorTextView = root.findViewById(R.id.date_calculator_errors)
        resultsTextView = root.findViewById(R.id.date_calculator_results)
    }

    private fun setViewsListeners() {
        fromDatePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            dateCalculatorViewModel.dateFrom.value = LocalDate.of(year, monthOfYear, dayOfMonth)
        }
        toDatePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            dateCalculatorViewModel.dateTo.value = LocalDate.of(year, monthOfYear, dayOfMonth)
        }

    }
    private fun setViewModelObservers() {
        dateCalculatorViewModel.dateFrom.observe(viewLifecycleOwner, {
            dateCalculatorViewModel.dateDifference.value = dateCalculatorViewModel.dateTo.value?.let { it1 -> getDateDifferential(it, it1) }
        })

        dateCalculatorViewModel.dateTo.observe(viewLifecycleOwner, {
            dateCalculatorViewModel.dateDifference.value = dateCalculatorViewModel.dateFrom.value?.let { it1 -> getDateDifferential(it1, it) }
        })

        dateCalculatorViewModel.dateDifference.observe(viewLifecycleOwner, { it ->
            if (it >= 0) {
                (getString(R.string.day_diff_equals) + it + getString(R.string.days)).also { resultsTextView.text = it }
                errorTextView.text = ""
            }
            else {
                resultsTextView.text = ""
                errorTextView.text = getString(R.string.cant_calculate_date_diff)
            }
        })
    }
}