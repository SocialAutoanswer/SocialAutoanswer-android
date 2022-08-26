package ru.bibaboba.core_android

import androidx.viewbinding.ViewBinding

interface AdapterCallback<DATA : Any, B : ViewBinding>{

    fun bindViews(binding : B, item: DATA, position: Int)

    fun onViewClicked(position: Int, item: DATA)

    fun onViewLongCLicked(position: Int, item: DATA)

}