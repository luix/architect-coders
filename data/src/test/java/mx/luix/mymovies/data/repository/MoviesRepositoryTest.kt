package mx.luix.mymovies.data.repository

import mx.luix.mymovies.data.source.LocalDataSource
import mx.luix.mymovies.data.source.LocationDataSource
import mx.luix.mymovies.data.source.RemoteDataSource
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var regionRepository: RegionRepository

    lateinit var moviesRepository: MoviesRepository

    private val apiKey = "1a2b3c4d"

    @Before
    fun setUp() {
        moviesRepository =
            MoviesRepository(localDataSource, remoteDataSource, regionRepository, apiKey)
    }
}