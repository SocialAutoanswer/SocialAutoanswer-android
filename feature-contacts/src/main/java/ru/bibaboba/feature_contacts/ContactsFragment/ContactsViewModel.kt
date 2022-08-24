package ru.bibaboba.feature_contacts.ContactsFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.Disposable
import ru.bibaboba.core_db.ContactRepository
import ru.bibaboba.core_entities.Contact
import ru.bibaboba.core_utils.Searcher
import ru.bibaboba.core_utils.SimpleTextWatcher
import javax.inject.Inject

class ContactsViewModel: ViewModel() {

    @Inject lateinit var repository: ContactRepository

    private val searcher = ru.bibaboba.core_utils.Searcher<Contact>()

    private val contacts = MutableLiveData<ArrayList<Contact>>()
    private val contact = MutableLiveData<Contact>()

    fun setContactsObserver(observer: (ArrayList<Contact>) -> Unit) =
        contacts.observeForever(observer)

    fun setContactObserver(observer: (Contact) -> Unit) =
        contact.observeForever(observer)


    fun getAllContacts(): Disposable = repository.getAllContacts()
        .subscribe({
            contacts.postValue(it as ArrayList<Contact>)
        }, {

        })

    fun addContact(contact: Contact): Disposable = repository.addContact(contact)
        .subscribe({
            this.contact.postValue(contact)
        },{

        })

    fun deleteContacts(contactsId: List<Int>): Disposable = repository.deleteContacts(contactsId)
        .subscribe({},{})

    val searchTextWatcher = object: SimpleTextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val request = s.toString()

            if(request == ""){
                getAllContacts()
                return
            }

            repository.getAllContacts()
                .subscribe({
                    contacts.postValue(
                        searcher.search(
                            request,
                            it.toTypedArray()
                        ))
                },{

                })


        }

    }


}