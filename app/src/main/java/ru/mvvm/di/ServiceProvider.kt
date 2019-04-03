package ru.mvvm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.mvvm.service.GetContactsService

@Module
abstract class ServiceProvider {

    @ContributesAndroidInjector
    abstract fun provideGetContactsService(): GetContactsService
}