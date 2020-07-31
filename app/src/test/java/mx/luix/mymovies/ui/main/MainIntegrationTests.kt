package mx.luix.mymovies.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import mx.luix.mymovies.data.source.LocalDataSource
import mx.luix.mymovies.testshared.mockedMovie
import mx.luix.mymovies.ui.FakeLocalDataSource
import mx.luix.mymovies.ui.defaultFakeMovies
import mx.luix.mymovies.ui.initMockedDi
import mx.luix.mymovies.ui.main.MainViewModel.UiModel
import mx.luix.mymovies.usecases.GetPopularMovies
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainIntegrationTests : AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<UiModel>

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        val vmModule = module {
            factory { MainViewModel(get(), get()) }
            factory { GetPopularMovies(get()) }
        }

        initMockedDi(vmModule)
        vm = get()
    }

    @Test
    fun `data is loaded from server when local source is empty`() {
        vm.model.observeForever(observer)

        vm.onCoarsePermissionRequested()

        verify(observer).onChanged(UiModel.Content(defaultFakeMovies))
    }

    @Test
    fun `data is loaded from local source when available`() {
        val fakeLocalMovies = listOf(mockedMovie.copy(10), mockedMovie.copy(11))
        val localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.movies = fakeLocalMovies
        vm.model.observeForever(observer)

        vm.onCoarsePermissionRequested()

        verify(observer).onChanged(UiModel.Content(fakeLocalMovies))
    }
}