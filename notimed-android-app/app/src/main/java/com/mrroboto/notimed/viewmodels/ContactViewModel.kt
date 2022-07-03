package com.mrroboto.notimed.viewmodels

import androidx.lifecycle.ViewModel
import com.mrroboto.notimed.repositories.ContactRepository

class ContactViewModel(private val repository: ContactRepository ):ViewModel(){
    val contacts = repository.contacts
}