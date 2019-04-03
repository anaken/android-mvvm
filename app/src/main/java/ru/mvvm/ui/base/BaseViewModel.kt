package ru.mvvm.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    val isLoading = ObservableBoolean()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}