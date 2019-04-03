package ru.mvvm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.mvvm.data.local.db.dao.ContactDao
import ru.mvvm.data.local.db.model.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [DateConverter::class])
abstract class AppDb : RoomDatabase() {

    abstract fun contactDao(): ContactDao
}