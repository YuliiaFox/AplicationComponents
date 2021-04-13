package com.example.contactshw2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactshw2.ContactDetails.DetailsFragment
import com.example.contactshw2.ContactsList.ContactsFragment
import com.example.contactshw2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, ContactsFragment())
            .commit()
    }

    override fun openContactDetails(contact: Contact) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, DetailsFragment.newInstance(contact))
            .addToBackStack(DetailsFragment::class.java.simpleName)
            .commit()
    }
}