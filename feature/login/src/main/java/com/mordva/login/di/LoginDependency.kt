package com.mordva.login.di

import com.mordva.security.external.SecurityRepository
import io.ktor.client.HttpClient

interface LoginDependency {
    val client: HttpClient
    val securityRepository: SecurityRepository
}