package ru.mvvm.ui.contacts

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import ru.mvvm.data.local.db.AppDb
import ru.mvvm.data.local.db.model.ContactEntity
import ru.mvvm.service.GetContactsFinishedInteractor
import ru.mvvm.ui.base.BaseViewModel
import ru.mvvm.utils.RxUtils
import java.util.concurrent.TimeUnit

class ContactsListViewModel(
    appDb: AppDb,
    getContactsFinishedInteractor: GetContactsFinishedInteractor
) : BaseViewModel() {

    private val pagedListConfig = Config(
        pageSize = 30,
        enablePlaceholders = true,
        maxSize = 200
    )

    private val searchText: MutableLiveData<String> = MutableLiveData()

    val contactsList: LiveData<PagedList<ContactEntity>> = Transformations.switchMap(searchText) { text ->
        appDb.contactDao().findByQuery("%$text%").toLiveData(pagedListConfig)
    }

    val contactsListAdapter = ContactsListAdapter()

    private val searchSubject = PublishSubject.create<String>()

    val searchOnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {
            searchSubject.onNext(newText ?: "")
            return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            searchSubject.onNext(query ?: "")
            return false
        }
    }

    init {
        isLoading.set(true)
        getContactsFinishedInteractor.onFinished {
            isLoading.set(false)
        }
        searchText.value = ""
        compositeDisposable.add(RxUtils.consume(searchSubject.map { text -> text.toLowerCase().trim() }
            .debounce(250, TimeUnit.MILLISECONDS), Consumer { text ->
            searchText.value = text
        }))
    }
}
