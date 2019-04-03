package ru.mvvm.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.mvvm.data.local.db.model.ContactEntity
import ru.mvvm.databinding.ContactsListItemBinding
import ru.mvvm.ui.base.BaseViewHolder

class ContactsListAdapter : PagedListAdapter<ContactEntity, ContactsListAdapter.ContactViewHolder>(diffCallback) {

    var onContactClick: ((ContactEntity) -> Unit)? = null

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ContactEntity>() {
            override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val contactsListItemBinding = ContactsListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContactViewHolder(contactsListItemBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class ContactViewHolder(private val contactsListItemBinding: ContactsListItemBinding) :
        BaseViewHolder(contactsListItemBinding.root) {

        private var contactsListItemViewModel: ContactsListItemViewModel? = null

        fun bindTo(contactEntity: ContactEntity?) {
            contactEntity?.let {
                contactsListItemViewModel = ContactsListItemViewModel(contactEntity) { contactEntity ->
                    onContactClick?.invoke(contactEntity)
                }
                contactsListItemBinding.viewModel = contactsListItemViewModel
            }
        }
    }
}

