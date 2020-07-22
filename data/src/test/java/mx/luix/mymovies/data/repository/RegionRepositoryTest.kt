package mx.luix.mymovies.data.repository

import mx.luix.mymovies.data.source.LocationDataSource
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegionRepositoryTest {

    @Mock
    lateinit var locationDataSource: LocationDataSource

    @Mock
    lateinit var permissionChecker: PermissionChecker

    private lateinit var regionRepository: RegionRepository

    @Before
    fun setUp() {
        regionRepository = RegionRepository(locationDataSource, permissionChecker)
    }
}