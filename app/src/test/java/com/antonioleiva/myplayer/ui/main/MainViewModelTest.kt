package com.antonioleiva.myplayer.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.antonioleiva.myplayer.data.Filter
import com.antonioleiva.myplayer.data.MediaItem
import com.antonioleiva.myplayer.data.MediaItem.Type
import com.antonioleiva.myplayer.ui.Event
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: MainViewModel
    private val fakeMediaProvider = FakeMediaProvider()

    @Before
    fun setUp() {
        vm = MainViewModel(fakeMediaProvider, coroutinesTestRule.testDispatcher)
    }

    @Test
    fun `progress is set visible when progressVisible value changes`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Boolean>>()
            vm.progressVisible.observeForever(observer)

            vm.onFilterSelected(Filter.None)

            verify(observer).onChanged(true)
        }

    @Test
    fun `navigates to detail when onItemClicked`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mock<Observer<Event<Int>>>()
            vm.navigateToDetail.observeForever(observer)

            val mediaItem = MediaItem(1, "", "", Type.PHOTO)
            vm.onItemClicked(mediaItem)

            verify(observer).onChanged(Event(1))
        }

    @Test
    fun `updates items when onFilterClicked`() {
        val observer = mock<Observer<List<MediaItem>>>()
        vm.items.observeForever(observer)

        vm.onFilterSelected(Filter.None)

        verify(observer).onChanged(fakeMediaProvider.getItems())
    }

}