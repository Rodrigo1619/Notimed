package com.mrroboto.notimed.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrroboto.notimed.data.models.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contacts_table")
    suspend fun getAllContacts() : List<Contact>

    @Query("DELETE FROM contacts_table WHERE _id LIKE :id")
    suspend fun removerContact(id: String)

    @Query("SELECT * FROM contacts_table WHERE _id LIKE :id")
    suspend fun getContact(id: String) : Contact
}
