package ru.mvvm.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

object RxUtils {

    fun <R> consume(
        observable: Observable<R>, onNext: Consumer<R> = onNext()
    ): Disposable {
        return RxUtils.consume(observable, onNext, null)
    }

    fun <R> consume(
        observable: Observable<R>, onNext: Consumer<R> = onNext(),
        onError: Consumer<Throwable>? = null
    ): Disposable {
        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onNext.accept(it)
            }, { throwable ->
                onError?.accept(throwable)
            })
    }

    fun <R> callable(
        callable: () -> R,
        onNext: Consumer<R> = onNext(),
        onError: Consumer<Throwable>? = null
    ): Disposable {
        return RxUtils.consume(Observable.fromCallable(callable), onNext, onError)
    }

    private fun <R> onNext(): Consumer<R> {
        return Consumer {}
    }
}