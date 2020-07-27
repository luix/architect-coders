package mx.luix.mymovies

import android.app.Application
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import mx.luix.mymovies.data.AndroidPermissionChecker
import mx.luix.mymovies.data.PlayServicesLocationDataSource
import mx.luix.mymovies.data.database.MovieDatabase
import mx.luix.mymovies.data.database.RoomDataSource
import mx.luix.mymovies.data.repository.MoviesRepository
import mx.luix.mymovies.data.repository.RegionRepository
import mx.luix.mymovies.data.repository.PermissionChecker
import mx.luix.mymovies.data.server.TheMovieDb
import mx.luix.mymovies.data.server.TheMovieDbDataSource
import mx.luix.mymovies.data.source.LocalDataSource
import mx.luix.mymovies.data.source.LocationDataSource
import mx.luix.mymovies.data.source.RemoteDataSource
import mx.luix.mymovies.ui.detail.DetailActivity
import mx.luix.mymovies.ui.detail.DetailViewModel
import mx.luix.mymovies.ui.main.MainActivity
import mx.luix.mymovies.ui.main.MainViewModel
import mx.luix.mymovies.usecases.FindMovieById
import mx.luix.mymovies.usecases.GetPopularMovies
import mx.luix.mymovies.usecases.ToggleMovieFavorite
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apiKey")) { androidApplication().getString(R.string.api_key) }
    single { MovieDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { TheMovieDbDataSource(get()) }
    factory<LocationDataSource> { PlayServicesLocationDataSource(get()) }
    factory<PermissionChecker> { AndroidPermissionChecker(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://api.themoviedb.org/3/" }
    single { TheMovieDb(get(named("baseUrl"))) }
}

val dataModule = module {
    factory { RegionRepository(get(), get()) }
    factory { MoviesRepository(get(), get(), get(), get(named("apiKey"))) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetPopularMovies(get()) }
    }

    scope(named<DetailActivity>()) {
        viewModel { (id: Int) -> DetailViewModel(id, get(), get(), get()) }
        scoped { FindMovieById(get()) }
        scoped { ToggleMovieFavorite(get()) }
    }
}