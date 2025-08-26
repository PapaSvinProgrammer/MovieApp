package com.mordva.collectionlist.di

import com.mordva.domain.repository.CollectionRepository

interface CollectionDependency {
    val collectionRepository: CollectionRepository
}