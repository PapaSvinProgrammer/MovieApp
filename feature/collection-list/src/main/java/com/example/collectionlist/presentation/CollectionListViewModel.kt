package com.example.collectionlist.presentation

import androidx.lifecycle.ViewModel
import com.example.collectionusecase.GetCollectionAll
import com.example.collectionusecase.GetCollectionByCategory
import com.example.collectionusecase.GetCollectionBySlug
import com.example.collectionusecase.model.CollectionParams
import com.example.ui.uiState.CollectionUIState
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import com.example.utils.multiRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

internal class CollectionListViewModel @Inject constructor(
    private val getCollectionAll: GetCollectionAll,
    private val getCollectionByCategory: GetCollectionByCategory,
    private val getCollectionBySlug: GetCollectionBySlug
) : ViewModel() {
    private var page = 1

    private val _collectionState = MutableStateFlow(CollectionUIState.Loading as CollectionUIState)
    val collectionState: StateFlow<CollectionUIState> = _collectionState

    fun getAllCollections() = launchWithoutOld(GET_ALL_JOB) {
        getCollectionAll.execute(page).onSuccess {
            _collectionState.value = CollectionUIState.Success(it)
        }
    }

    fun loadMoreAllCollections() = launchWithoutOld(LOAD_ALL_JOB) {
        page++

        getCollectionAll.execute(page).onSuccess {
            val temp = (collectionState.value as CollectionUIState.Success)
                .data
                .toMutableList()

            temp.addAll(it)
            _collectionState.value = CollectionUIState.Success(temp)
        }
    }

    fun getCollectionsByCategory(category: String) = launchWithoutOld(GET_BY_CATEGORY_JOB) {
        val params = CollectionParams(
            category = category
        )

        getCollectionByCategory.execute(params).onSuccess {
            _collectionState.value = CollectionUIState.Success(it)
        }
    }

    fun loadMoreCollectionsByCategory(category: String) = launchWithoutOld(LOAD_BY_CATEGORY_JOB) {
        page++

        val params = CollectionParams(
            page = page,
            category = category
        )

        getCollectionByCategory.execute(params).onSuccess { collections ->
            val temp = (collectionState.value as CollectionUIState.Success).data.toMutableList()
            temp.addAll(collections)
            _collectionState.value = CollectionUIState.Success(temp)
        }
    }

    fun getCollectionsByListId(list: List<String>) = launchWithoutOld(GET_BY_LIST_ID_JOB) {
        val temp = multiRequest(list) { slug ->
            getCollectionBySlug.execute(slug)
        }

        _collectionState.value = CollectionUIState.Success(temp)
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_ALL_JOB = "get_all_collections"
        const val LOAD_ALL_JOB = "load_more_all_collections"
        const val GET_BY_CATEGORY_JOB = "get_collections_by_category"
        const val LOAD_BY_CATEGORY_JOB = "load_more_collections_by_category"
        const val GET_BY_LIST_ID_JOB = "get_collections_by_list_id"
    }
}
