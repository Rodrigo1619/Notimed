package com.mrroboto.notimed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrroboto.notimed.databinding.CardreminderLayoutBinding
import com.mrroboto.notimed.data.models.Reminder

class ReminderAdapter : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {
    inner class ReminderViewHolder(private val binding: CardreminderLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(reminder: Reminder){
            binding.reminder = reminder
            binding.executePendingBindings()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ReminderViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cardreminder_layout,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        reminders?.let{
            holder.bind(it[position])
        }
    }


    override fun getItemCount() = reminders?.size ?: 0

    private var reminders:List<Reminder>? = null

    fun setData(data: List<Reminder>){
        reminders = data
        notifyDataSetChanged()
    }
}