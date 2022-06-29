package com.mrroboto.notimed.repositories
import androidx.lifecycle.MutableLiveData
import com.mrroboto.notimed.data.models.Contact

class ContactRepository {
    private var _contacts = listOf(
        Contact("Fernando", "1234-5678", "Opico", "General","12:00PM", "13:00PM"),
        Contact("Juan", "1234-5679", "Opico", "Ginecologo","1:00PM", "17:00PM"),
        Contact("Wilmer", "2234-5678", "Santa tecla", "General","16:00PM", "18:00PM"),
        Contact("Rodrigo", "1134-5678", "Soyapango", "General","10:00AM", "11:00AM")
    ).toMutableList()

    val contacts: MutableLiveData<List<Contact>>
        get() = MutableLiveData(_contacts)

    fun addContact(contact: Contact){
        _contacts.add(contact)
        contacts.value = _contacts
    }
}