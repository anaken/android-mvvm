package ru.mvvm.data.local.db.dao

import androidx.paging.DataSource
import androidx.room.*
import ru.mvvm.data.local.db.model.ContactEntity

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contacts: List<ContactEntity>)

    @Update
    fun update(contactEntity: ContactEntity)

    @Query("SELECT * FROM ContactEntity ORDER BY name COLLATE NOCASE ASC")
    fun getAll(): DataSource.Factory<Int, ContactEntity>

    @Query("DELETE FROM ContactEntity")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM ContactEntity")
    fun count(): Int

    @Query("SELECT * FROM ContactEntity WHERE id = :id")
    fun findById(id: String): ContactEntity

    @Query("SELECT * FROM ContactEntity WHERE name like :query OR phone like :query ORDER BY name COLLATE NOCASE ASC")
    fun findByQuery(query: String): DataSource.Factory<Int, ContactEntity>
}