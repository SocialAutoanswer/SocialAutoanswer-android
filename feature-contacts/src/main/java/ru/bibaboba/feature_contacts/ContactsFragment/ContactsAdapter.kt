package ru.bibaboba.feature_contacts.ContactsFragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import ru.bibaboba.core_android.AdapterCallback
import ru.bibaboba.core_android.BaseRecyclerViewAdapter
import ru.bibaboba.core_entities.Contact
import ru.bibaboba.feature_contacts.databinding.ItemContactBinding

class ContactsAdapter: BaseRecyclerViewAdapter<Contact, ItemContactBinding>() {

    val selectedContacts = mutableListOf<Contact>()
    private val isSelected = HashMap<Int, Boolean>()

    private var onContactClick: (Int, Contact) -> Unit = { pos: Int, contact: Contact -> }
    private var onContactLongClick: (Int, Contact) -> Unit = { pos: Int, contact: Contact -> }

    fun setOnContactClickListener(listener: (Int, Contact) -> Unit){
        onContactClick = listener
    }

    fun setOnContactLongClickListener(listener: (Int, Contact) -> Unit){
        onContactLongClick = listener
    }


    fun changeState(pos: Int){

        if(pos in isSelected){
            isSelected[pos] = !isSelected[pos]!!
        }else{
            isSelected[pos] = true
        }

        notifyItemChanged(pos)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun unselectAll(){
        for(key in isSelected.keys){
            isSelected[key] = false
        }

        isSelected.clear()
        notifyDataSetChanged()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun selectAll(){
        for(key in isSelected.keys){
            isSelected[key] = true
        }

        notifyDataSetChanged()
    }

    fun getState(pos: Int) = isSelected[pos]

    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): ViewHolder<Contact, ItemContactBinding> = ViewHolder(
        ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        object : AdapterCallback<Contact, ItemContactBinding>{

            override fun bindViews(binding: ItemContactBinding, item: Contact, position: Int) {
                binding.name.text = item.name
                binding.description.text = item.description
                binding.chosenMark.visibility =
                    if(isSelected[position] == true) ViewGroup.VISIBLE else ViewGroup.INVISIBLE
            }

            override fun onViewClicked(position: Int, item: Contact) = onContactClick(position, item)
            override fun onViewLongCLicked(position: Int, item: Contact) = onContactLongClick(position, item)

        }
    )


}