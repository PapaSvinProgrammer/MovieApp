package com.mordva.images_list.presentation

import androidx.lifecycle.ViewModel
import com.mordva.images_list.domain.GetMovieImages
import com.mordva.images_list.domain.model.ImagesParams
import com.mordva.images_list.presentation.widget.UIState
import com.mordva.images_list.util.getData
import com.mordva.model.image.ImageType
import com.mordva.ui.uiState.ImageUIState
import com.mordva.util.cancelAllJobs
import com.mordva.util.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class ImageListViewModel @Inject constructor(
    private val getMovieImages: GetMovieImages
) : ViewModel() {
    private val _state = MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    fun updateImageTypes(type: ImageType) {
        if (type == ImageType.ALL) {
            setImageTypeAll()
            return
        }

        _state.update {
            it.copy(imageTypes = it.imageTypes - ImageType.ALL)
        }

        val newValue = if (type in state.value.imageTypes) {
            state.value.imageTypes.toMutableSet() - type
        } else {
            state.value.imageTypes.toMutableSet() + type
        }

        if (newValue.isNotEmpty()) {
            _state.update { it.copy(imageTypes = newValue) }
        } else {
            setImageTypeAll()
        }
    }

    fun getImages(movieId: Int) = launchWithoutOld(GET_IMAGES_JOB) {
        _state.update { it.copy(page = 1) }

        val params = ImagesParams(
            movieId = movieId,
            page = state.value.page,
            types = state.value.imageTypes
        )

        getMovieImages.execute(params).onSuccess { imageList ->
            _state.update {
                it.copy(imagesState = ImageUIState.Success(imageList))
            }
        }
    }

    fun loadMoreImages(movieId: Int) = launchWithoutOld(GET_IMAGES_JOB) {
        _state.update { it.copy(page = it.page + 1) }

        val params = ImagesParams(
            movieId = movieId,
            page = state.value.page,
            types = state.value.imageTypes
        )

        getMovieImages.execute(params).onSuccess { imageList ->
            val new = state.value.imagesState.getData().toMutableList()
            new.addAll(imageList)

            _state.update {
                it.copy(imagesState = ImageUIState.Success(new))
            }
        }
    }

    private fun setImageTypeAll() {
        _state.update { it.copy(imageTypes = setOf(ImageType.ALL)) }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_IMAGES_JOB = "get_images"
    }
}