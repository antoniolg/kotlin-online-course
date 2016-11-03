package com.antonioleiva.myplayer.detail.di

import com.antonioleiva.myplayer.detail.DetailActivity
import dagger.Subcomponent

@Subcomponent(modules = [(DetailModule::class)])
interface DetailComponent {
    fun inject(activity: DetailActivity)
}