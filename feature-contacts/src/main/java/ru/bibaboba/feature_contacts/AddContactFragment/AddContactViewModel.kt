package ru.bibaboba.feature_contacts.AddContactFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bibaboba.core_db.ContactRepository
import ru.bibaboba.core_entities.Contact
import javax.inject.Inject

class AddContactViewModel: ViewModel() {

    @Inject lateinit var repository: ContactRepository

    val contacts = MutableLiveData<Contact>()

    fun addContact(contact: Contact) = repository.addContact(contact)
        .subscribe({
            contacts.postValue(contact)
        },{

        })
}