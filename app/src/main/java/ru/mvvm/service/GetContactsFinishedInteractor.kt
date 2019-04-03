package ru.mvvm.service

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.ReplaySubject

class GetContactsFinishedInteractor {

    private val observable: ReplaySubject<Boolean> = ReplaySubject.createWithSize(1)

    fun submit(next: Boolean) {
        observable.onNext(next)
    }

    fun onFinished(onNext: (Boolean) -> Unit): Disposable {
        return observable.take(1).subscribe(onNext)
    }
}