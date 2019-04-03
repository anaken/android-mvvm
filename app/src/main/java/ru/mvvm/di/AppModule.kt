package ru.mvvm.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.mvvm.BuildConfig
import ru.mvvm.Const
import ru.mvvm.data.local.db.AppDb
import ru.mvvm.data.remote.ApiService
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        const val JOB = "JOB"
        const val UI = "UI"
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    @Named(JOB)
    internal fun provideJobScheduler(): Scheduler {
        return Schedulers.computation()
    }

    @Provides
    @Singleton
    @Named(UI)
    internal fun provideUIScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    internal fun provideApiService(): ApiService {
        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideAppDb(context: Context): AppDb {
        return Room.databaseBuilder(context, AppDb::class.java, Const.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}