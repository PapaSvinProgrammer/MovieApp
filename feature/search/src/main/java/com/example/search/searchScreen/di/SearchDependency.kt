package com.example.search.searchScreen.di

import com.example.data.external.CategoryRepository
import com.example.data.external.CollectionRepository
import com.example.data.external.HistoryRepository
import com.example.data.external.MovieRepository
import com.example.data.external.PersonRepository

interface SearchDependency {
    val collectionRepository: CollectionRepository
    val personRepository: PersonRepository
    val movieRepository: MovieRepository
    val categoryRepository: CategoryRepository
    val historyRepository: HistoryRepository
}