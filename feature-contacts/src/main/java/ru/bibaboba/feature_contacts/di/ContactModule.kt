package ru.bibaboba.feature_contacts.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.bibaboba.core_db.ContactDAO
import ru.bibaboba.core_db.ContactRepository
import ru.bibaboba.core_db.Database
import javax.inject.Singleton


@Module
class ContactModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDataBase() = Room.databaseBuilder(
        context.applicationContext,
        Database::class.java,
        "contact_database"
    ).build()

    @Provides
    fun provideContactDao(database: Database) = database.getContactDao()

    @Provides
    fun provideRepository(dao: ContactDAO) = ContactRepository(dao)
}