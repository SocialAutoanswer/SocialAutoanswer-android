package ru.bibaboba.feature_contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.bibaboba.core_android.AdapterCallback
import ru.bibaboba.core_android.BaseRecyclerViewAdapter
import ru.bibaboba.feature_contacts.Enteties.Contact
import ru.bibaboba.feature_contacts.databinding.FragmentContacsBinding.inflate
import ru.bibaboba.feature_contacts.databinding.ItemContactBinding

class ContactsAdapter: BaseRecyclerViewAdapter<Contact, ItemContactBinding>() {

    private var onContactClick: (Contact) -> Unit = {}

    fun setOnContactClickListener(listener: (Contact) -> Unit){
        onContactClick = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): ViewHolder<Contact, ItemContactBinding> = ViewHolder(
        ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        object : AdapterCallback<Contact, ItemContactBinding>{
            override fun bindViews(binding: ItemContactBinding, item: Contact, position: Int) {
            }

            override fun onViewClicked(view: View, item: Contact) = onContactClick(item)

        }
    )


}