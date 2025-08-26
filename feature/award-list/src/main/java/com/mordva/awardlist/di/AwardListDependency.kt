package com.mordva.awardlist.di

import com.mordva.domain.repository.AwardRepository

interface AwardListDependency {
    val awardRepository: AwardRepository
}