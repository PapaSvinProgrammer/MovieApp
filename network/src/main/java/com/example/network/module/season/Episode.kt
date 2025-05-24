package com.example.network.module.season

data class Episode(
    val number: Int,
    val name: String,
    val enName: String,
    val airDate: String,
    val description: String,
    val enDescription: String,
    val still: Still,
)