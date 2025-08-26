package com.mordva.personscreen.di

import com.mordva.domain.repository.AwardRepository
import com.mordva.domain.repository.MovieRepository
import com.mordva.domain.repository.PersonRepository

interface PersonDependency {
    val personRepository: PersonRepository
    val movieRepository: MovieRepository
    val awardRepository: AwardRepository
}