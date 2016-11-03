package com.antonioleiva.myplayer

import android.app.Application
import com.antonioleiva.myplayer.di.AppComponent
import com.antonioleiva.myplayer.di.AppModule
import com.antonioleiva.myplayer.di.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}