package ru.bibaboba.feature_contacts.ContactsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bibaboba.core_android.createRecyclerView
import ru.bibaboba.core_android.overplaceEmptyList
import ru.bibaboba.core_entities.Contact
import ru.bibaboba.core_utils.ChoosingListener
import ru.bibaboba.feature_contacts.*
import ru.bibaboba.feature_contacts.databinding.FragmentContactsBinding
import ru.bibaboba.feature_contacts.di.ContactComponent

const val CONTROLLER_BUNDLE = "controller"

class ContactsFragment : Fragment(), ContactRecyclerController {


    private val viewModel: ContactsViewModel = ViewModelProvider
        .NewInstanceFactory()
        .create(ContactsViewModel::class.java)

    private lateinit var binding: FragmentContactsBinding
    private val adapter by lazy { ContactsAdapter() }
    private var recyclerEmptyFlag = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        ContactComponent.init(requireContext()).inject(viewModel)

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        binding.searchText.addTextChangedListener(viewModel.getSearcherTextWatcher())

        viewModel.getAllContacts()
        viewModel.setContactsObserver{ adapter.setItems(it) }
        viewModel.setContactObserver { adapter.addItem(it) }
        viewModel.setAmountObserver { fillContainer(it) }

        binding.addContactButton.setOnClickListener{ openAddContactFragment() }
        binding.deleteContactsButton.setOnClickListener{ deleteContacts() }

        adapter.setOnContactClickListener { pos: Int, item: Contact ->
            if(adapter.selectedContacts.isEmpty())
                findNavController().navigate(ru.bibaboba.core_navigation.R.id.redactContactFragment)
            else
                changeItemState(pos, item)
        }

        adapter.setOnContactLongClickListener { pos: Int, item: Contact ->
            if(adapter.selectedContacts.isEmpty())
                ChoosingListener.onChoosingListener.isChoosing(true)

            changeItemState(pos, item)
        }

        return binding.root
    }

    //реализация ContactRecyclerController
    override fun addContact(contact: Contact) { viewModel.addContact(contact) }
    //конец


    private fun fillContainer(amount: Int){

        val onNotifyButtonClick: (view:View) -> Unit = { openAddContactFragment() }

        if(amount == 0){
            recyclerEmptyFlag = true

            binding.container.removeAllViews()
            binding.container.addView(overplaceEmptyList(
                binding.container,
                R.drawable.ic_round_checkmark,
                R.string.notify_zero_contacts,
                R.string.add_contact,
                onNotifyButtonClick
            ))
        } else{
            recyclerEmptyFlag = false

            binding.container.removeAllViews()
            binding.container.addView(
                createRecyclerView(requireContext(), adapter, LinearLayoutManager(requireContext()))
            )
        }

    }


    private fun openAddContactFragment(){
        findNavController().navigate(
            ru.bibaboba.core_navigation.R.id.addContactFragment,
            bundleOf(CONTROLLER_BUNDLE to this)
        )
    }

    private fun changeItemState(pos: Int, contact: Contact){
        adapter.changeState(pos)

        if(adapter.getState(pos) == true){
            adapter.selectedContacts.add(contact)
        }else{
            adapter.selectedContacts.remove(contact)
        }

        binding.deleteContactsButton.visibility =
            if(adapter.selectedContacts.isEmpty()) View.INVISIBLE else View.VISIBLE

        if(adapter.selectedContacts.isEmpty())
            ChoosingListener.onChoosingListener.isChoosing(false)

    }

    private fun deleteContacts(){

        val chosenContactsId = mutableListOf<Int>()

        adapter.selectedContacts.forEach{ chosenContactsId.add(it.id) }

        adapter.deleteItems(adapter.selectedContacts)
        viewModel.deleteContacts(chosenContactsId)

        binding.deleteContactsButton.visibility = View.INVISIBLE

        adapter.unselectAll()
        adapter.selectedContacts.clear()
        ChoosingListener.onChoosingListener.isChoosing(false)
    }


}

