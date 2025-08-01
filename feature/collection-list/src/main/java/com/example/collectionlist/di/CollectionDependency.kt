package com.example.collectionlist.di

import com.example.data.external.CollectionRepository

interface CollectionDependency {
    val collectionRepository: CollectionRepository
}