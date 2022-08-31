package ru.bibaboba.core_utils


interface ItemSelectingListener {

    fun isChoosing(delete: () -> Unit, cancel: () -> Unit)
    fun notChoosing()

}