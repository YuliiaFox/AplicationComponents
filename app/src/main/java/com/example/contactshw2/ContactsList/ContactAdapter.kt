package com.example.contactshw2.ContactsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactshw2.Contact
import com.example.contactshw2.databinding.ContactItemBinding

class ContactAdapter(private val onContactClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<ContactViewHolder>() {
    var dataSet: List<Contact> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context))
        return ContactViewHolder(binding, onContactClickListener)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}