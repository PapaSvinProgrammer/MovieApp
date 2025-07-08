package com.example.movieScreen.domain

import com.example.model.image.CollectionMovie
import javax.inject.Inject

class FilterCollection @Inject constructor() {
    fun execute(list: List<CollectionMovie>): List<CollectionMovie> {
        return list.filter {
            it.slug != "hd"
        }
    }
}