package com.example.personscreen.di

import com.example.data.external.AwardRepository
import com.example.data.external.MovieRepository
import com.example.data.external.PersonRepository

interface PersonDependency {
    val personRepository: PersonRepository
    val movieRepository: MovieRepository
    val awardRepository: AwardRepository
}