package com.antonioleiva.myplayer

import com.antonioleiva.myplayer.main.MainPresenter
import com.antonioleiva.myplayer.model.MediaItem
import com.antonioleiva.myplayer.model.Provider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

class MainPresenterTest {

    private val view = mock<MainPresenter.View>()
    private val provider = mock<Provider>()
    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        presenter = MainPresenter(view, provider)
    }

    @Test
    fun providerIsCalledInOnCreate() {
        presenter.onCreate()
        verify(provider).dataAsync(any())
    }

    @Test
    fun progressIsShownInOnCreate() {
        presenter.onCreate()
        verify(view).showProgress()
    }

    @Test
    fun uiIsUpdatedWithResults() {
        presenter.onCreate()
        val media = mock<List<MediaItem>>()

        argumentCaptor<(List<MediaItem>) -> Unit>().apply {
            verify(provider).dataAsync(capture())
            val f = allValues[0]
            f(media)

            verify(view).updateData(media)
        }
    }

}