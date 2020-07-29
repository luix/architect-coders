package mx.luix.mymovies.ui

import mx.luix.mymovies.data.source.LocalDataSource
import mx.luix.mymovies.domain.Movie
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


class FakeLocalDataSource : LocalDataSource {

    var movies: List<Movie> = emptyList()

    override suspend fun isEmpty() = movies.isEmpty()

    override suspend fun saveMovies(movies: List<Movie>) {
        this.movies = movies
    }

    override suspend fun getPopularMovies(): List<Movie> = movies

    override suspend fun findById(id: Int): Movie = movies.first { it.id == id }

    override suspend fun update(movie: Movie) {
        movies = movies.filterNot { it.id == movie.id } + movie
    }
}

