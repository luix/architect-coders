package mx.luix.mymovies.data.repository

import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import mx.luix.mymovies.data.repository.PermissionChecker.Permission.COARSE_LOCATION
import mx.luix.mymovies.data.source.LocationDataSource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
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

    @Test
    fun `returns default when coarse permission not granted`() {
        runBlocking {

            whenever(permissionChecker.check(COARSE_LOCATION)).thenReturn(false)

            val region = regionRepository.findLastRegion()

            assertEquals(RegionRepository.DEFAULT_REGION, region)
        }
    }
}