package com.example.contactshw2

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactshw2.Contact
import com.example.contactshw2.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {
    private val contacts: MutableLiveData<List<Contact>> = MutableLiveData()

    fun getContacts() : LiveData<List<Contact>> {
        return contacts
    }

    fun loadContacts(context: Context) {
        val repo = ContactsRepository(context)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getContactList()
            contacts.postValue(result)
        }
    }
}