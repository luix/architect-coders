package mx.luix.mymovies.usecases

import mx.luix.mymovies.data.repository.MoviesRepository
import mx.luix.mymovies.domain.Movie

class FindMovieById(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(id: Int): Movie = moviesRepository.findById(id)
}