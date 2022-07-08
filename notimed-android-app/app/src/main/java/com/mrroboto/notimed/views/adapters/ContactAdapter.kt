package com.mrroboto.notimed.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mrroboto.notimed.R
import com.mrroboto.notimed.data.models.Contact
import com.mrroboto.notimed.databinding.CardcontactLayoutBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){
    inner class ContactViewHolder(private val biding: CardcontactLayoutBinding):RecyclerView.ViewHolder(biding.root){
        fun bind(contact: Contact){
            biding.contact = contact
            biding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cardcontact_layout,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contacts?.let {
            holder.bind(it[position])
        }
    }
    override fun getItemCount() = contacts?.size ?: 0

    private var contacts:List<Contact>? = null

    fun setData(data: List<Contact>){
        contacts = data
        notifyDataSetChanged()
    }

    private var onItemClickListener : ((id : String) -> Unit)? = null
    private var onItemListenerPosition : ((position: Int) -> Unit)? = null

    private var onUpdateId : ((id : String) -> Unit)? = null
    private var onUpdatePosition : ((position: Int) -> Unit)? = null
    fun getContactId(listener: (id: String) -> Unit) {
        onItemClickListener = listener
    }

    fun getPosition(listener: (position: Int) -> Unit) {
        onItemListenerPosition = listener
    }

    fun getContactIdforUpdate(listener: (id: String) -> Unit) {
        onUpdateId = listener
    }

    fun getPositionforUpdate(listener: (position: Int) -> Unit) {
        onUpdatePosition = listener
    }

}