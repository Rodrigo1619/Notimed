package com.mrroboto.notimed.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mrroboto.notimed.R
import com.mrroboto.notimed.data.models.Appointment
import com.mrroboto.notimed.databinding.CardappointmentLayoutBinding


class AppointmentAdapter : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {
    lateinit var binding: CardappointmentLayoutBinding

    inner class AppointmentViewHolder(val binding: CardappointmentLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(appointment: Appointment){
            binding.appointment = appointment
            binding.executePendingBindings()
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):AppointmentViewHolder {

        return AppointmentViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cardappointment_layout,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        appointments?.let{
            holder.bind(it[position])
        }
        holder.binding.deleteButtonAppointment.setOnClickListener{
            onItemClickListener?.let{
                it(appointments?.get(position)?._id.toString())
            }
        }

        holder.binding.editButtonAppointment.setOnClickListener{
            onUpdateId?.let {
                it(appointments?.get(position)?._id.toString())
            }
            onUpdatePosition?.let {
                it(position)
            }
            holder.binding.root.findNavController().navigate(R.id.action_appointmentFragment_to_updateAppointment)
        }
    }


    override fun getItemCount() = appointments?.size ?: 0

    private var appointments:List<Appointment>? = null

    fun setData(data: List<Appointment>){
        appointments = data
        notifyDataSetChanged()
    }

    private var onItemClickListener : ((id:String)-> Unit)? = null
    private var onItemListenerPosition : ((position: Int) -> Unit)? = null

    private var onUpdateId : ((id: String)->Unit)?=null
    private var onUpdatePosition : ((position: Int) -> Unit)? = null

    fun getAppointmentId(listener: (id:String)->Unit){
        onItemClickListener = listener
    }
    fun getPosition(listener: (position: Int) -> Unit) {
        onItemListenerPosition = listener
    }
    fun getAppointmentIdforUpdate(listener: (id: String) -> Unit) {
        onUpdateId = listener
    }
    fun getPositionforUpdate(listener: (position: Int) -> Unit) {
        onUpdatePosition = listener
    }
}
