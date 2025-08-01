package com.example.settings.di

import com.example.data.external.PreferencesRepository

interface SettingsDependency {
    val preferencesRepository: PreferencesRepository
}