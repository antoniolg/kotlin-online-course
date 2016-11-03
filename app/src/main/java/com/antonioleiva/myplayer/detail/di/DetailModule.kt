package com.antonioleiva.myplayer.detail.di

import com.antonioleiva.myplayer.detail.DetailActivity
import com.antonioleiva.myplayer.detail.DetailPresenter
import com.antonioleiva.myplayer.model.Provider
import dagger.Module
import dagger.Provides

@Module
class DetailModule(private val activity: DetailActivity) {
    @Provides fun provideDetailPresenter(provider: Provider) = DetailPresenter(activity, provider)
}