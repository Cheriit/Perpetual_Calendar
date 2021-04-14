package com.cherit.perpetualcalendar.ui.views.shopping_sunday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cherit.perpetualcalendar.R
import com.cherit.perpetualcalendar.ui.views.year_selection.YearSelectionViewModel

class ShoppingSundayFragment : Fragment() {

    private lateinit var shoppingSundayViewModel: ShoppingSundayViewModel
    private lateinit var yearSelectedViewModel: YearSelectionViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        shoppingSundayViewModel =
                ViewModelProvider(this).get(ShoppingSundayViewModel::class.java)
        yearSelectedViewModel =
            ViewModelProvider(this).get(YearSelectionViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_shopping_sunday, container, false)

        shoppingSundayViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}