package mx.luix.mymovies.usecases

import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import mx.luix.mymovies.data.repository.MoviesRepository
import mx.luix.mymovies.testshared.mockedMovie
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
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

    @Test
    fun `invoke calls movies repository`() {
        runBlocking {

            val movie = mockedMovie.copy(id = 1)

            val result = toggleMovieFavorite.invoke(movie)

            verify(moviesRepository).update(result)
        }
    }

    @Test
    fun `favorite movie becomes unfavorite`() {
        runBlocking {

            val movie = mockedMovie.copy(favorite = true)

            val result = toggleMovieFavorite.invoke(movie)

            assertFalse(result.favorite)
        }
    }
}