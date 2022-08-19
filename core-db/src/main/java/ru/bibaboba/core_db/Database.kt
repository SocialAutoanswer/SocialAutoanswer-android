package ru.bibaboba.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bibaboba.core_entities.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = true)
abstract class Database: RoomDatabase() {

    abstract fun getContactDao(): ContactDAO

}