package com.example.usersapp.presentation.app

import android.app.Application
import com.example.usersapp.presentation.di.DaggerAppComponent
import com.example.usersapp.presentation.di.DiProvider

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        val appComponent = DaggerAppComponent
            .builder()
            .addContext(this)
            .build()
        DiProvider.appComponent = appComponent
    }
}