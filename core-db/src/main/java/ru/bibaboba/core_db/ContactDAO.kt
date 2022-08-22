package ru.bibaboba.core_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.bibaboba.core_entities.Contact

@Dao
interface ContactDAO {

    @Query("select * from Contacts")
    fun getAllContacts(): Maybe<List<Contact>>

    @Query("select * from Contacts where id = :id")
    fun getContactById(id: Int): Single<Contact>

    @Insert
    fun insertContact(contact: Contact): Completable

    @Query("delete from contacts where id in (:contactsId)")
    fun deleteContacts(contactsId: List<Int>): Completable
}