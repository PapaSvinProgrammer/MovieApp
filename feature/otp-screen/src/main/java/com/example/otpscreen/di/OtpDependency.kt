package com.example.otpscreen.di

import com.example.data.external.PreferencesRepository
import com.example.security.external.KeyStoreRepository

interface OtpDependency {
    val preferencesRepository: PreferencesRepository
    val keyStoreRepository: KeyStoreRepository
}