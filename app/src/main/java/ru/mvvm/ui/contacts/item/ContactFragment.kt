package ru.mvvm.ui.contacts.item

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import ru.mvvm.BR
import ru.mvvm.databinding.FragmentContactBinding
import ru.mvvm.ui.Layout
import ru.mvvm.ui.base.BaseFragment
import javax.inject.Inject


@Layout(id = ru.mvvm.R.layout.fragment_contact)
class ContactFragment : BaseFragment<FragmentContactBinding, ContactViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private var contactViewModel: ContactViewModel? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): ContactViewModel {
        contactViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactViewModel::class.java)
        contactViewModel!!.contactId = arguments?.get("id") as String
        return contactViewModel!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactViewModel?.phoneCall?.observe(this, Observer { phone ->
            phoneCall(phone)
        })
        viewDataBinding.toolbar.setNavigationOnClickListener {
            Navigation.findNavController(rootView!!).popBackStack()
        }
    }

    private fun phoneCall(phone: String) {
        Permissions.check(activity, Manifest.permission.CALL_PHONE, null, object : PermissionHandler() {
            override fun onGranted() {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
                context?.startActivity(intent)
            }
        })
    }

    abstract class ViewModelProviderFactory : ViewModelProvider.NewInstanceFactory()
}
