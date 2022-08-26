package ru.bibaboba.core_utils

object ChoosingListener {

    lateinit var onChoosingListener: ItemChoosingListener

    fun setOnItemChoosingListener(listener: ItemChoosingListener){
        onChoosingListener = listener
    }

}