package com.mrroboto.notimed.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrroboto.notimed.R
import com.mrroboto.notimed.data.models.Reminder
import com.mrroboto.notimed.databinding.CardreminderLayoutBinding

class ReminderAdapter : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {
    lateinit var binding: CardreminderLayoutBinding

    inner class ReminderViewHolder(val binding: CardreminderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reminder: Reminder) {
            binding.reminder = reminder
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {

        return ReminderViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cardreminder_layout,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        reminders?.let {
            holder.bind(it[position])
        }

        holder.binding.deleteButtonReminder.setOnClickListener {
            onItemClickListener?.let {
                it(reminders?.get(position)?._id.toString())
            }

            onItemListenerPosition?.let {
                it(position)
            }
        }
    }


    override fun getItemCount() = reminders?.size ?: 0

    private var reminders: List<Reminder>? = null

    fun setData(data: List<Reminder>) {
        reminders = data
        notifyDataSetChanged()
    }

    private var onItemClickListener : ((id : String) -> Unit)? = null
    private var onItemListenerPosition : ((position: Int) -> Unit)? = null
    fun getReminderId(listener: (id: String) -> Unit) {
        onItemClickListener = listener
    }

    fun getPosition(listener: (position: Int) -> Unit) {
        onItemListenerPosition = listener
    }
}