package com.example.search.searchResult.di

import com.example.search.searchResult.SearchResultViewModel
import dagger.Component

@Component(
    modules = [SearchResultModule::class]
)
@SearchResultScope
interface SearchResultComponent {
    @Component.Factory
    interface Factory {
        fun create(): SearchResultComponent
    }

    val viewModel: SearchResultViewModel
}