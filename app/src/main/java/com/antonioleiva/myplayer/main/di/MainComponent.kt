package com.antonioleiva.myplayer.main.di

import com.antonioleiva.myplayer.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [(MainModule::class)])
interface MainComponent {
    fun inject(activity: MainActivity)
}