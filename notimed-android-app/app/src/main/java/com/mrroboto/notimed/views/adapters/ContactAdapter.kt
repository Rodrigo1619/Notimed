package com.mrroboto.notimed.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mrroboto.notimed.R
import com.mrroboto.notimed.data.models.Contact
import com.mrroboto.notimed.databinding.CardcontactLayoutBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    lateinit var binding: CardcontactLayoutBinding

    inner class ContactViewHolder(val binding: CardcontactLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(contact: Contact){
            binding.contact = contact
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ContactViewHolder {
        return ContactViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cardcontact_layout,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contacts?.let {
            holder.bind(it[position])
        }

        holder.binding.editButtonContact.setOnClickListener {
            onUpdateId?.let {
                it(contacts?.get(position)?._id.toString())
            }
            onUpdatePosition?.let {
                it(position)
            }
            holder.binding.root.findNavController().navigate(R.id.action_contactFragment_to_updateContact)
        }
    }
    override fun getItemCount() = contacts?.size ?: 0

    private var contacts:List<Contact>? = null

    fun setData(data: List<Contact>){
        contacts = data
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