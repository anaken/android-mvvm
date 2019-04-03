package ru.mvvm.ui.main

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    internal fun provideContactsListViewModelFactory(
        errorsInteractor: ErrorsInteractor
    ): MainActivity.ViewModelProviderFactory {
        return object : MainActivity.ViewModelProviderFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(errorsInteractor) as T
            }
        }
    }
}