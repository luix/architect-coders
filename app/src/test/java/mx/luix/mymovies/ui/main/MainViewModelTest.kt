package mx.luix.mymovies.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import mx.luix.mymovies.usecases.GetPopularMovies
import mx.luix.mymovies.ui.main.MainViewModel.UiModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getPopularMovies: GetPopularMovies

    @Mock
    lateinit var observer: Observer<UiModel>

    private lateinit var vm: MainViewModel

    @Before
    fun setUp() {
        vm = MainViewModel(getPopularMovies, Dispatchers.Unconfined)
    }

    @Test
    fun `observing LiveData launches location permission request`() {

        vm.model.observeForever(observer)

        verify(observer).onChanged(UiModel.RequestLocationPermission)
    }
}