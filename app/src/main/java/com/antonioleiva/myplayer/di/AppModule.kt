package com.antonioleiva.myplayer.di

import com.antonioleiva.myplayer.App
import com.antonioleiva.myplayer.model.MediaProvider
import com.antonioleiva.myplayer.model.Provider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(val app: App) {
    @Provides @Singleton fun provideApp() = app
    @Provides @Singleton fun provideMediaProvider(): Provider = MediaProvider
}