package ru.bibaboba.feature_contacts.ContactsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.bibaboba.core_entities.Contact
import ru.bibaboba.feature_contacts.*
import ru.bibaboba.feature_contacts.AddContactFragment.AddContactFragment
import ru.bibaboba.feature_contacts.databinding.FragmentContactsBinding

class ContactsFragment : Fragment(), ContactRecyclerController {


    private val viewModel: ContactsViewModel = ViewModelProvider
        .NewInstanceFactory()
        .create(ContactsViewModel::class.java)

    private lateinit var binding: FragmentContactsBinding
    private val adapter by lazy { ContactsAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        ContactComponent.init(requireContext()).inject(viewModel)

        viewModel.getAllContacts()
        binding.recycler.adapter = adapter

        viewModel.setContactsObserver{
            adapter.setItems(it)
        }

        binding.addContactButton.setOnClickListener{ openAddContactFragment()}

        adapter.setOnContactClickListener {
            findNavController().navigate(ru.bibaboba.core_navigation.R.id.redactContactFragment)
        }

        return binding.root
    }



    override fun addContact(contact: Contact) {
        viewModel.addContact(contact)
        adapter.addItem(contact)
    }


    private fun openAddContactFragment() {
        val dialog = AddContactFragment()
        val args = Bundle()
        args.putSerializable("controller", this)
        dialog.arguments = args
        dialog.show(requireActivity().supportFragmentManager, "")
    }

}

