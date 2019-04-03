package ru.mvvm.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.android.AndroidInjection
import io.reactivex.functions.Consumer
import ru.mvvm.data.local.db.AppDb
import ru.mvvm.data.local.db.model.ContactEntity
import ru.mvvm.data.remote.ApiService
import ru.mvvm.ui.main.ErrorsInteractor
import ru.mvvm.utils.RxUtils
import ru.mvvm.utils.ioThread
import javax.inject.Inject

class GetContactsService : Service() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var appDb: AppDb

    @Inject
    lateinit var getContactsFinishedInteractor: GetContactsFinishedInteractor

    @Inject
    lateinit var errorsInteractor: ErrorsInteractor

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ioThread {
            val contactsCount = appDb.contactDao().count()
            if (contactsCount > 0) {
                getContactsFinishedInteractor.submit(true)
            }
        }
        getContacts()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun getContacts() {
        RxUtils.consume(apiService.getData1()
            .mergeWith(apiService.getData2())
            .mergeWith(apiService.getData3())
            .take(3)
            .doOnComplete {
                getContactsFinishedInteractor.submit(true)
            }, Consumer { contacts ->
            ioThread {
                appDb.contactDao().insert(contacts.map { ContactEntity.from(it) })
            }
        }, Consumer {
            errorsInteractor.submit(it)
            getContactsFinishedInteractor.submit(true)
        })
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
