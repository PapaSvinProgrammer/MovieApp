package com.example.awardlist.di

import com.example.data.external.AwardRepository

interface AwardDependency {
    val awardRepository: AwardRepository
}