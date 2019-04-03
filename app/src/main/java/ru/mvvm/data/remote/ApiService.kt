package ru.mvvm.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import ru.mvvm.data.remote.model.ContactRes

interface ApiService {

    @GET("mobile-test-droid/master/json/generated-01.json")
    fun getData1(): Observable<List<ContactRes>>

    @GET("mobile-test-droid/master/json/generated-02.json")
    fun getData2(): Observable<List<ContactRes>>

    @GET("mobile-test-droid/master/json/generated-03.json")
    fun getData3(): Observable<List<ContactRes>>
}