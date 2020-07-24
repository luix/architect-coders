package mx.luix.mymovies.usecases

import mx.luix.mymovies.data.repository.MoviesRepository
import mx.luix.mymovies.domain.Movie

class GetPopularMovies(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(): List<Movie> = moviesRepository.getPopularMovies()
}