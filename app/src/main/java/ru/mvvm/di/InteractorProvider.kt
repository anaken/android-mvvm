package ru.mvvm.di

import dagger.Module
import dagger.Provides
import ru.mvvm.service.GetContactsFinishedInteractor
import ru.mvvm.ui.main.ErrorsInteractor
import javax.inject.Singleton

@Module
class InteractorProvider {

    @Provides
    @Singleton
    internal fun provideGetContactsFinishedInteractor(): GetContactsFinishedInteractor {
        return GetContactsFinishedInteractor()
    }

    @Provides
    @Singleton
    internal fun provideErrorsInteractor(): ErrorsInteractor {
        return ErrorsInteractor()
    }
}
