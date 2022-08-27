package ru.bibaboba.core_utils

import androidx.activity.OnBackPressedCallback

interface ItemChoosingListener {

    fun isChoosing(delete: () -> Unit, cancel: () -> Unit)
    fun notChoosing()

}