package com.zancheema.android.pantry

import android.app.Application
import com.zancheema.android.pantry.container.AppContainer

class PantryApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}