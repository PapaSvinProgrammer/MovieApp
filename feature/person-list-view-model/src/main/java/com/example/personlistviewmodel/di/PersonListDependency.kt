package com.example.personlistviewmodel.di

import com.example.data.external.PersonRepository

interface PersonListDependency {
    val personRepository: PersonRepository
}