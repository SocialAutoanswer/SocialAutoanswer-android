package ru.bibaboba.feature_contacts

import ru.bibaboba.core_entities.Contact
import java.io.Serializable

interface ContactRecyclerController : Serializable {

    fun addContact(contact: Contact)

}