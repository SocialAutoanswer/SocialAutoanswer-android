package ru.bibaboba.core_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.bibaboba.core_utils.Searchable
import java.io.Serializable

@Entity(tableName = "Contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int = 0,
    @ColumnInfo(name="name")
    val name: String = "",
    @ColumnInfo(name="description")
    val description: String = "",
    ): Serializable, Searchable{

    override fun containRequest(
        requestChecker: (request: String, whereSearch: String) -> Boolean,
        request: String
    ): Boolean = requestChecker(request, name) || requestChecker(request, description)
}