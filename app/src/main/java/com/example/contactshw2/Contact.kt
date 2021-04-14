package com.example.contactshw2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String,
    val phone: List<String>,
    val email: List<String>,
) : Parcelable