package com.mordva.base_view_models.person_list

import com.mordva.domain.repository.PersonRepository

interface PersonListDependency {
    val personRepository: PersonRepository
}