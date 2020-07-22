package mx.luix.mymovies.data.source

import mx.luix.mymovies.domain.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(apiKey: String, region: String): List<Movie>
}