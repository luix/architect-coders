package mx.luix.mymovies.usecases

import mx.luix.mymovies.data.repository.MoviesRepository
import mx.luix.mymovies.domain.Movie

class ToggleMovieFavorite(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(movie: Movie): Movie = with(movie) {
        copy(favorite = !favorite).also { moviesRepository.update(it) }
    }
}