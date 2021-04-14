package com.example.contactshw2

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract.Contacts.*

class ContactsRepository(private val context: Context) {
    private val cursor: Cursor? = context.contentResolver.query(CONTENT_URI, null, null, null, null)

    fun getContactList(): List<Contact> {
        if (cursor == null) return emptyList()
        val contactsList = mutableListOf<Contact>()

        while (cursor.moveToNext()) {
            val contactId = cursor.getString(cursor.getColumnIndex(_ID))
            val name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME))
            var phones = emptyList<String>()
            var emails = emptyList<String>()

            val phoneCursor = getPhoneCursor(contactId)
            if (phoneCursor != null) {
                phones = getData(phoneCursor, Phone.NUMBER)
            }

            val emailCursor = getEmailsCursor(contactId)

            if (emailCursor != null) {
                emails = getData(emailCursor, Email.ADDRESS)
            }

            if (name.isNullOrBlank() && phones.isEmpty()) {
                phoneCursor?.close()
                continue
            }
            if (phones.isEmpty()) {
                contactsList.add(Contact(name, emptyList(), emails))
            } else {
                contactsList.add(Contact(name, phones, emails))
            }
            phoneCursor?.close()
            emailCursor?.close()
        }
        return contactsList
    }

    private fun getData(cursor: Cursor, data: String): List<String> {
        val dataList = mutableListOf<String>()
        while (cursor.moveToNext()) {
            dataList.add(cursor.getString(cursor.getColumnIndex(data)))
        }
        return dataList
    }

    private fun getPhoneCursor(contactId: String): Cursor? {
        return context.contentResolver.query(
            Phone.CONTENT_URI,
            null,
            "${Phone.CONTACT_ID} = $contactId",
            null,
            null
        )
    }

    private fun getEmailsCursor(contactId: String): Cursor? {
        return context.contentResolver.query(
            Email.CONTENT_URI,
            null,
            "${Email.CONTACT_ID} = $contactId",
            null,
            null
        )
    }
}