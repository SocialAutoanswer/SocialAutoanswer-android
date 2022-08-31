package ru.bibaboba.feature_contacts.AddContactFragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.bibaboba.core_entities.Contact
import ru.bibaboba.core_utils.SimpleTextWatcher
import ru.bibaboba.feature_contacts.ContactRecyclerController
import ru.bibaboba.feature_contacts.ContactsFragment.CONTROLLER_BUNDLE
import ru.bibaboba.feature_contacts.R
import ru.bibaboba.feature_contacts.databinding.FragmentAddContactBinding


class AddContactFragment : Fragment() {

    private val controller by lazy { arguments?.getSerializable(CONTROLLER_BUNDLE) as ContactRecyclerController}
    private lateinit var binding: FragmentAddContactBinding

    private val viewModel: AddContactViewModel = ViewModelProvider
        .NewInstanceFactory()
        .create(AddContactViewModel::class.java)

    private val textWatcher = object: SimpleTextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.name.isActivated = false
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)

        binding.saveButton.setOnClickListener{ save(binding) }
        binding.cancelButton.setOnClickListener{ cancel() }
        binding.avatar.setOnClickListener{ addAvatar() }

        binding.name.addTextChangedListener(textWatcher)

        return binding.root
    }

    private fun cancel(){ requireActivity().onBackPressed() }

    private fun save(binding: FragmentAddContactBinding){

        val name = binding.name
        val description = binding.description

        if(name.text.isEmpty()){
            name.isActivated = true
            name.hint = "Это поле обязательно!"
            return
        }

        controller.addContact(
            Contact(
                name = binding.name.text.toString(),
                description = viewModel.setDescription(description.text.toString())
            )
        )

        cancel()
    }

    private fun getAvatar(){


    }

    private fun addAvatar(){

    }

}