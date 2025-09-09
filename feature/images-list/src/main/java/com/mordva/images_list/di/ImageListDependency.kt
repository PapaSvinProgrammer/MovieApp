package com.mordva.images_list.di

import com.mordva.domain.repository.ImageRepository

interface ImageListDependency {
    val imageRepository: ImageRepository
}