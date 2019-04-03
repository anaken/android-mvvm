package ru.mvvm.ui.main

import androidx.lifecycle.MutableLiveData
import ru.mvvm.ui.base.BaseViewModel

class MainViewModel(
    errorsInteractor: ErrorsInteractor
) : BaseViewModel() {

    val errorsData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        errorsInteractor.subscribe { errorsData.value = it }
    }
}