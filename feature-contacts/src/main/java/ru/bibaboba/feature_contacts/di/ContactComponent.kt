package ru.bibaboba.feature_contacts.di

import android.content.Context
import dagger.Component
import ru.bibaboba.feature_contacts.AddContactFragment.AddContactViewModel
import ru.bibaboba.feature_contacts.ContactsFragment.ContactsViewModel
import javax.inject.Singleton

@Component(modules = [ContactModule::class])
@Singleton
interface ContactComponent {

    fun inject(viewModel: AddContactViewModel)
    fun inject(viewModel: ContactsViewModel)

    companion object{

        fun init(context: Context): ContactComponent = DaggerContactComponent.builder()
            .contactModule(ContactModule(context))
            .build()
    }

}