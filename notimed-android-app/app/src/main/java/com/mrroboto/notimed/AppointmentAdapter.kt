package com.mrroboto.notimed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrroboto.notimed.data.models.Appointment
import com.mrroboto.notimed.databinding.CardappointmentLayoutBinding


class AppointmentAdapter : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {
    inner class AppointmentViewHolder(private val binding: CardappointmentLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(appointment: Appointment){
            binding.appointment = appointment
            binding.executePendingBindings()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        AppointmentViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cardappointment_layout,
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        appointments?.let{
            holder.bind(it[position])
        }
    }


    override fun getItemCount() = appointments?.size ?: 0

    private var appointments:List<Appointment>? = null

    fun setData(data: List<Appointment>){
        appointments = data
        notifyDataSetChanged()
    }
}