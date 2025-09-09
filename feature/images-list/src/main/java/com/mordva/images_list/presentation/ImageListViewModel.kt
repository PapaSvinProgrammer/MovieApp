package com.mordva.images_list.presentation

import androidx.lifecycle.ViewModel
import com.mordva.images_list.domain.GetMovieImages
import com.mordva.images_list.presentation.widget.UIState
import com.mordva.model.image.ImageType
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
            _state.update { it.copy(imageTypes = setOf(ImageType.ALL)) }
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

        _state.update {
            it.copy(imageTypes = newValue)
        }
    }

    fun getImages(movieId: Int) = launchWithoutOld(GET_IMAGES_JOB) {
//        val params = ImagesParams(
//            movieId = movieId,
//            page = 1,
//            types = state.value.imageTypes
//        )
//
//        getMovieImages.execute(params).onSuccess { imageList ->
//            _state.update {
//                it.copy(imagesState = ImageUIState.Success(imageList))
//            }
//        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_IMAGES_JOB = "get_images"
    }
}