package com.cherit.perpetualcalendar.ui.shopping_sunday

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cherit.perpetualcalendar.R

class ShoppingSundayFragment : Fragment() {

    private lateinit var notificationsViewModel: ShoppingSundayViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(ShoppingSundayViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shopping_sunday, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}