package ru.mvvm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.mvvm.ui.contacts.ContactsListFragment
import ru.mvvm.ui.contacts.ContactsListModule
import ru.mvvm.ui.contacts.item.ContactFragment
import ru.mvvm.ui.contacts.item.ContactModule
import ru.mvvm.ui.main.MainActivity
import ru.mvvm.ui.main.MainModule

@Module
abstract class ActivityProvider {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ContactsListModule::class])
    abstract fun provideContactsListFragment(): ContactsListFragment

    @ContributesAndroidInjector(modules = [ContactModule::class])
    abstract fun provideContactFragment(): ContactFragment
}