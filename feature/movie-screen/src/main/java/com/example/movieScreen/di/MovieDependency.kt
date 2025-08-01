package com.example.movieScreen.di

import com.example.data.external.CollectionRepository
import com.example.data.external.CommentRepository
import com.example.data.external.MovieRepository
import com.example.data.external.PersonRepository

interface MovieDependency {
    val movieRepository: MovieRepository
    val collectionRepository: CollectionRepository
    val commentRepository: CommentRepository
    val personRepository: PersonRepository
}