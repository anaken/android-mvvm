package ru.mvvm.ui.contacts

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import ru.mvvm.BR
import ru.mvvm.R
import ru.mvvm.databinding.FragmentContactsListBinding
import ru.mvvm.ui.Layout
import ru.mvvm.ui.base.BaseFragment
import javax.inject.Inject

@Layout(id = R.layout.fragment_contacts_list)
class ContactsListFragment : BaseFragment<FragmentContactsListBinding, ContactsListViewModel>() {

    private var contactsViewModel: ContactsListViewModel? = null

    @Inject
    lateinit var viewModelFactory: ContactsListViewModelProviderFactory

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): ContactsListViewModel {
        contactsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactsListViewModel::class.java)
        return contactsViewModel!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsViewModel!!.contactsListAdapter.onContactClick = { contact ->
            Navigation.findNavController(rootView!!).navigate(
                R.id.action_contacts_list_to_contact,
                bundleOf("id" to contact.id)
            )
        }
        contactsViewModel!!.contactsList.observe(this, Observer { list ->
            contactsViewModel!!.contactsListAdapter.submitList(list)
        })
        viewDataBinding.contactsList.requestFocus()
        viewDataBinding.search.setOnQueryTextListener(contactsViewModel!!.searchOnQueryTextListener)
    }

    abstract class ContactsListViewModelProviderFactory : ViewModelProvider.NewInstanceFactory()
}
