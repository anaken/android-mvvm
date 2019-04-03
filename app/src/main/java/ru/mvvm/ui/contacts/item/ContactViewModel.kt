package ru.mvvm.ui.contacts.item

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import ru.mvvm.data.local.db.AppDb
import ru.mvvm.data.local.db.model.ContactEntity
import ru.mvvm.ui.base.BaseViewModel
import ru.mvvm.utils.ioThread


class ContactViewModel(
    private val appDb: AppDb
) : BaseViewModel() {

    var contactName: ObservableField<String> = ObservableField()
    var contactPhone: ObservableField<String> = ObservableField()
    var contactTemperament: ObservableField<String> = ObservableField()

    var contact: ContactEntity? = null

    val phoneCall: MutableLiveData<String> = MutableLiveData()

    var contactId: String? = null
        set(contactId) {
            field = contactId
            contactId?.let {
                ioThread {
                    contact = appDb.contactDao().findById(contactId)
                    contactName.set(contact!!.name)
                    contactPhone.set(contact!!.phone)
                    contactTemperament.set(contact!!.temperament)
                }
            }
        }

    fun onPhoneClick() {
        contact?.phone.let {
            phoneCall.value = it
        }
    }
}