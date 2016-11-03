package com.antonioleiva.myplayer.main.di

import com.antonioleiva.myplayer.main.MainActivity
import com.antonioleiva.myplayer.main.MainPresenter
import com.antonioleiva.myplayer.model.Provider
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity) {
    @Provides fun provideMainPresenter(provider: Provider) = MainPresenter(activity, provider)
}