package ru.bibaboba.core_utils

interface Searchable {

    fun containRequest(
        requestChecker : (request : String, whereSearch : String) -> Boolean,
        request : String
    ) : Boolean

}