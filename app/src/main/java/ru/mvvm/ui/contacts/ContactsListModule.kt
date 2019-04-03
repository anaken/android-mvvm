package ru.mvvm.ui.contacts

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import ru.mvvm.data.local.db.AppDb
import ru.mvvm.service.GetContactsFinishedInteractor

@Module
class ContactsListModule {

    @Provides
    internal fun provideContactsListViewModelFactory(
        appDb: AppDb,
        getContactsFinishedInteractor: GetContactsFinishedInteractor
    ): ContactsListFragment.ContactsListViewModelProviderFactory {
        return object : ContactsListFragment.ContactsListViewModelProviderFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ContactsListViewModel(appDb, getContactsFinishedInteractor) as T
            }
        }
    }
}