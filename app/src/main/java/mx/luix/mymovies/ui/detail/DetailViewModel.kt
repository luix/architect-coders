package mx.luix.mymovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import mx.luix.mymovies.domain.Movie
import mx.luix.mymovies.ui.common.ScopedViewModel
import mx.luix.mymovies.usecases.FindMovieById
import mx.luix.mymovies.usecases.ToggleMovieFavorite

class DetailViewModel(
    private val movieId: Int,
    private val findMovieById: FindMovieById,
    private val toggleMovieFavorite: ToggleMovieFavorite,
    override val uiDispatcher: CoroutineDispatcher
) :
    ScopedViewModel(uiDispatcher) {

    data class UiModel(val movie: Movie)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findMovie()
            return _model
        }

    private fun findMovie() = launch {
        _model.value = UiModel(findMovieById.invoke(movieId))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.movie?.let {
            _model.value = UiModel(toggleMovieFavorite.invoke(it))
        }
    }
}