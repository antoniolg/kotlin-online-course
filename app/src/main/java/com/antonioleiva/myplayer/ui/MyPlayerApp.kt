package com.antonioleiva.myplayer.ui

import android.app.Application
import com.antonioleiva.myplayer.data.MediaProvider
import com.antonioleiva.myplayer.data.MediaProviderImpl
import com.antonioleiva.myplayer.ui.detail.DetailActivity
import com.antonioleiva.myplayer.ui.detail.DetailViewModel
import com.antonioleiva.myplayer.ui.main.MainActivity
import com.antonioleiva.myplayer.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

class MyPlayerApp : Application() {

    private val appModule = module {
        single<MediaProvider> { MediaProviderImpl }
        single<CoroutineContext>(named("ioDispatcher")) { Dispatchers.IO }
    }

    private val scopesModule = module {
        scope<MainActivity> {
            viewModel { MainViewModel(get(), get(named("ioDispatcher"))) }
        }

        scope<DetailActivity> {
            viewModel { DetailViewModel(get(), get(named("ioDispatcher"))) }
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyPlayerApp)
            modules(listOf(appModule, scopesModule))
        }
    }
}