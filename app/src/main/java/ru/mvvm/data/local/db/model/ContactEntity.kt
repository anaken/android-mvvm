package ru.mvvm.data.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mvvm.data.remote.model.ContactRes
import java.util.*

@Entity
data class ContactEntity(
    @PrimaryKey
    var id: String,

    var name: String? = null,

    var phone: String? = null,

    var height: Float? = null,

    var biography: String? = null,

    var temperament: String,

    var educationStart: Date? = null,

    var educationEnd: Date? = null
) {
    companion object {
        fun from(c: ContactRes): ContactEntity {
            return ContactEntity(
                c.id,
                c.name,
                c.phone,
                c.height,
                c.biography,
                c.temperament.type,
                c.educationPeriod?.start,
                c.educationPeriod?.end
            )
        }
    }
}