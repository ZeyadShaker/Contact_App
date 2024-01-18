package com.routecontactapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import de.hdodenhof.circleimageview.CircleImageView

class ContactAdapter(
    val contacts: List<Contact>?


) : Adapter<ContactAdapter.ContactsViewHolder>() {
    var itemOnClickListener: RecyclerViewInterface? = null
    var onPhotoClick: RecyclerViewInterface? = null

    class ContactsViewHolder(itemView: View) :
        ViewHolder(itemView) {
        val contactName: TextView = itemView.findViewById(R.id.name_avatar)
        val contactPhoneNumber: TextView = itemView.findViewById(R.id.phone_number_avatar)
        val contactLogo: CircleImageView = itemView.findViewById(R.id.logo_avatar)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts?.size ?: 0

    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        contacts?.let { contacts ->
            val currentContact = contacts[position]
            holder.contactName.text = currentContact.avatarName
            holder.contactPhoneNumber.text = currentContact.avatarPhoneNumber
            currentContact.avatarLogo?.let {
                holder.contactLogo.setImageResource(it)
            }
            holder.itemView.setOnClickListener {
                itemOnClickListener?.onContactClick(currentContact, position)

            }
            holder.contactLogo.setOnClickListener {
                onPhotoClick?.onContactClick(currentContact, position)

            }


        }
    }
}