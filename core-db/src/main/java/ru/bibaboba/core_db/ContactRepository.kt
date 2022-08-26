package ru.bibaboba.core_db

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.bibaboba.core_entities.Contact
import java.util.regex.Pattern

class ContactRepository(private val dao: ContactDAO) {


    fun addContact(contact: Contact) = dao.insertContact(contact)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

    fun getAllContacts() = dao.getAllContacts()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

    fun getContactById(id: Int) = dao.getContactById(id)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

    fun deleteContacts(contactsId: List<Int>) = dao.deleteContacts(contactsId)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

    fun contactCount() = dao.contactCount()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())

}

