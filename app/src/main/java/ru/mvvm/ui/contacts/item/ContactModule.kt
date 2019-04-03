package ru.mvvm.ui.contacts.item

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import ru.mvvm.data.local.db.AppDb

@Module
class ContactModule {

    @Provides
    internal fun provideContactsListViewModelFactory(appDb: AppDb):
            ContactFragment.ViewModelProviderFactory {
        return object : ContactFragment.ViewModelProviderFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ContactViewModel(appDb) as T
            }
        }
    }
}