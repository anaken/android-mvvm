package ru.mvvm.ui.contacts

import androidx.databinding.ObservableField
import ru.mvvm.data.local.db.model.ContactEntity

class ContactsListItemViewModel(
    private val contact: ContactEntity,
    private val contactItemClickListener: ((ContactEntity) -> Unit)
) {

    val contactName = ObservableField(contact.name)
    val contactPhone = ObservableField(contact.phone)

    fun onContactClick() {
        contactItemClickListener.invoke(contact)
    }
}