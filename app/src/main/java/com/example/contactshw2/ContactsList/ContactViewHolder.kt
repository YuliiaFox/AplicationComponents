package com.example.contactshw2.ContactsList

import androidx.recyclerview.widget.RecyclerView
import com.example.contactshw2.Contact
import com.example.contactshw2.databinding.ContactItemBinding

class ContactViewHolder(
    private val binding: ContactItemBinding,
    private val onContactClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact) {
        binding.root.setOnClickListener {
            onContactClickListener(adapterPosition)
        }
        if (contact.name.isBlank()) {
            binding.contactName.text = contact.phone[0]
        } else {
            binding.contactName.text = contact.name
            binding.phoneNumber.text = contact.phone.toString()
        }
    }
}