package com.cherit.perpetualcalendar.ui.components.event_list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cherit.perpetualcalendar.R
import com.cherit.perpetualcalendar.models.Event

class EventListRecyclerViewAdapter(
    private var values: List<Event> = arrayListOf()
): RecyclerView.Adapter<EventListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        (item.name + ":").also { holder.nameView.text = it }
        holder.dateView.text = item.getDate()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.event_name)
        val dateView: TextView = view.findViewById(R.id.event_date)

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }
}