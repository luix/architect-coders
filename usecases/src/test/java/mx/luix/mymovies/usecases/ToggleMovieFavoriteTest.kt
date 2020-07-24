package mx.luix.mymovies.usecases

import mx.luix.mymovies.data.repository.MoviesRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ToggleMovieFavoriteTest {

    @Mock
    lateinit var moviesRepository: MoviesRepository

    lateinit var toggleMovieFavorite: ToggleMovieFavorite

    @Before
    fun setUp() {
        toggleMovieFavorite = ToggleMovieFavorite(moviesRepository)
    }
}