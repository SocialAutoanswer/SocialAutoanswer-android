package ru.bibaboba.feature_contacts.AddContactFragment

import androidx.lifecycle.ViewModel
import ru.bibaboba.core_db.ContactRepository
import java.util.regex.Pattern
import javax.inject.Inject

class AddContactViewModel: ViewModel() {

    @Inject lateinit var repository: ContactRepository

    fun setDescription(description: String) =
        if (description == "" || Pattern.matches("[\\s]+", description))
            "Нет описания" else description



}