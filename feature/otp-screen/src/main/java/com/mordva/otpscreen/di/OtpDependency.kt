package com.mordva.otpscreen.di

import com.mordva.domain.repository.PreferencesRepository
import com.mordva.security.external.KeyStoreRepository

interface OtpDependency {
    val preferencesRepository: PreferencesRepository
    val keyStoreRepository: KeyStoreRepository
}