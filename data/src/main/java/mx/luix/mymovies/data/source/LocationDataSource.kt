package mx.luix.mymovies.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}