package ru.mvvm.ui.main

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class ErrorsInteractor {

    private val observable: PublishSubject<Throwable> = PublishSubject.create()

    fun submit(next: Throwable) {
        observable.onNext(next)
    }

    fun subscribe(onNext: (Throwable) -> Unit): Disposable {
        return observable.subscribe(onNext)
    }
}