package com.mrroboto.notimed.repositories
import androidx.lifecycle.MutableLiveData
import com.mrroboto.notimed.data.models.Contact

class ContactRepository {
    private var _contacts = listOf(
        Contact("Fernando", "1234-5678", "Opico", "General","12:00", "13:00")
    ).toMutableList()

    val contacts: MutableLiveData<List<Contact>>
        get() = MutableLiveData(_contacts)

    fun addContact(contact: Contact){
        _contacts.add(contact)
        contacts.value = _contacts
    }
}