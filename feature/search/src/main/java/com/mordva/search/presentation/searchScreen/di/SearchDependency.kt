package com.mordva.search.presentation.searchScreen.di

import com.mordva.domain.repository.CategoryRepository
import com.mordva.domain.repository.CollectionRepository
import com.mordva.domain.repository.HistoryRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository

interface SearchDependency {
    val collectionRepository: CollectionRepository
    val personRepository: PersonRepository
    val movieRepository: MovieRepository
    val categoryRepository: CategoryRepository
    val historyRepository: HistoryRepository
}