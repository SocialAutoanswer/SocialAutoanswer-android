package ru.bibaboba.core_utils

object SelectingListener {

    lateinit var onSelectingListener: ItemSelectingListener

    fun setOnItemSelectingListener(listener: ItemSelectingListener){
        onSelectingListener = listener
    }

}