package com.example.corecomponent

import com.example.data.external.AwardRepository
import com.example.data.external.CategoryRepository
import com.example.data.external.CollectionRepository
import com.example.data.external.CommentRepository
import com.example.data.external.HistoryRepository
import com.example.data.external.MovieRepository
import com.example.data.external.PersonRepository
import com.example.data.external.PreferencesRepository
import com.example.data.external.SeasonRepository
import com.example.data.external.StudioRepository

interface DataDependency {
    val categoryRepository: CategoryRepository
    val commentRepository: CommentRepository
    val movieRepository: MovieRepository
    val seasonRepository: SeasonRepository
    val awardRepository: AwardRepository
    val collectionRepository: CollectionRepository
    val personRepository: PersonRepository
    val studioRepository: StudioRepository
    val preferencesRepository: PreferencesRepository
    val historyRepository: HistoryRepository
}