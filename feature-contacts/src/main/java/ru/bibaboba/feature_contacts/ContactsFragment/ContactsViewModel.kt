package ru.bibaboba.feature_contacts.ContactsFragment

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.bibaboba.core_db.ContactRepository
import ru.bibaboba.core_entities.Contact
import javax.inject.Inject

class ContactsViewModel: ViewModel() {

    @Inject lateinit var repository: ContactRepository

    private val contacts = MutableLiveData<ArrayList<Contact>>()

    fun setContactsObserver(observer: (ArrayList<Contact>) -> Unit) =
        contacts.observeForever(observer)

    fun getAllContacts() = repository.getAllContacts()
        .subscribe({
            contacts.postValue(it as ArrayList<Contact>)
        }, {

        })

    fun addContact(contact: Contact) = repository.addContact(contact)
        .subscribe({},{})

}