package com.alejandro8a.androidTemplate

import android.app.Application
import androidx.multidex.MultiDex
import com.alejandro8a.androidTemplate.di.DatabaseModule
import com.alejandro8a.androidTemplate.di.NetworkModule
import com.alejandro8a.androidTemplate.di.RepositoryModule
import com.alejandro8a.androidTemplate.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(){

    private val TAG = MyApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(DatabaseModule, NetworkModule, RepositoryModule, ViewModelModule))
        }
    }
}