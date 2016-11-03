package com.antonioleiva.myplayer.di

import com.antonioleiva.myplayer.App
import com.antonioleiva.myplayer.detail.di.DetailComponent
import com.antonioleiva.myplayer.detail.di.DetailModule
import com.antonioleiva.myplayer.main.di.MainComponent
import com.antonioleiva.myplayer.main.di.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(app: App)
    fun plus(homeModule: MainModule): MainComponent
    fun plus(detailModule: DetailModule): DetailComponent
}