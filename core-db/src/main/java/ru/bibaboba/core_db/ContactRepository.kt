package ru.bibaboba.core_db

import io.reactivex.rxjava3.schedulers.Schedulers
import ru.bibaboba.core_entities.Contact

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

}