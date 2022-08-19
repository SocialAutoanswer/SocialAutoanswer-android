package ru.bibaboba.feature_contacts.AddContactFragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import ru.bibaboba.core_entities.Contact
import ru.bibaboba.feature_contacts.ContactComponent
import ru.bibaboba.feature_contacts.ContactRecyclerController
import ru.bibaboba.feature_contacts.databinding.FragmentAddContactBinding


class AddContactFragment : DialogFragment() {

    private val controller by lazy { arguments?.getSerializable("controller") as ContactRecyclerController}

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity.let{

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val binding = FragmentAddContactBinding.inflate(inflater)

            builder.setView(binding.root)
                .setPositiveButton("save") { dialog, id ->

                    controller.addContact(
                        Contact(
                            name = binding.name.text.toString(),
                            description = binding.description.text.toString()
                        )
                    )

                }

                .setNegativeButton("cancel") { dialog, id ->
                    dialog.cancel()
                }


            builder.create()
        }
    }

}