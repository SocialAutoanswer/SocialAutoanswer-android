package ru.bibaboba.feature_contacts.AddContactFragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import ru.bibaboba.core_entities.Contact
import ru.bibaboba.feature_contacts.ContactRecyclerController
import ru.bibaboba.feature_contacts.ContactsFragment.CONTROLLER_BUNDLE
import ru.bibaboba.feature_contacts.R
import ru.bibaboba.feature_contacts.databinding.FragmentAddContactBinding


class AddContactFragment : Fragment() {

    private val controller by lazy { arguments?.getSerializable(CONTROLLER_BUNDLE) as ContactRecyclerController}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddContactBinding.inflate(inflater, container, false)

        binding.saveButton.setOnClickListener{
            controller.addContact(
                Contact(
                    name = binding.name.text.toString(),
                    description = binding.description.text.toString()
                )
            )

            cancel()
        }

        binding.cancelButton.setOnClickListener{ cancel() }


        return binding.root
    }

    fun cancel(){ requireActivity().onBackPressed() }

}