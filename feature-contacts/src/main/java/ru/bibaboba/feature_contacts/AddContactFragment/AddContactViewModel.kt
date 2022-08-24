package ru.bibaboba.feature_contacts.AddContactFragment

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.bibaboba.core_db.ContactRepository
import ru.bibaboba.core_entities.Contact
import ru.bibaboba.core_utils.SimpleTextWatcher
import ru.bibaboba.feature_contacts.databinding.FragmentAddContactBinding
import java.util.regex.Pattern
import javax.inject.Inject

class AddContactViewModel: ViewModel() {

    @Inject lateinit var repository: ContactRepository

    fun setDescription(description: String) =
        if (description == "" || Pattern.matches("[\\s]+", description))
            "Нет описания" else description

}