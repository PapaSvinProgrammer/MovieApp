package com.mordva.login.di

import com.mordva.security.external.SecurityRepository

interface LoginDependency {
    val securityRepository: SecurityRepository
}