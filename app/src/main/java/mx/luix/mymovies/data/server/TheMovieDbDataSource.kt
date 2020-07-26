package mx.luix.mymovies.data.server

import mx.luix.mymovies.data.source.RemoteDataSource
import mx.luix.mymovies.domain.Movie
import toDomainMovie

class TheMovieDbDataSource(private val theMovieDb: TheMovieDb) : RemoteDataSource {

    override suspend fun getPopularMovies(apiKey: String, region: String): List<Movie> =
        theMovieDb.service
            .listPopularMoviesAsync(apiKey, region)
            .results
            .map { it.toDomainMovie() }
}