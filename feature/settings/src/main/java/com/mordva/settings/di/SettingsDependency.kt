package com.mordva.settings.di

import com.mordva.domain.repository.PreferencesRepository

interface SettingsDependency {
    val preferencesRepository: PreferencesRepository
}