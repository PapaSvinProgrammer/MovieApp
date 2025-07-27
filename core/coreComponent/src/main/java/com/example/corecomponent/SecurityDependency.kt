package com.example.corecomponent

import com.example.security.external.KeyStoreRepository

interface SecurityDependency {
    val keyStoreRepository: KeyStoreRepository
}