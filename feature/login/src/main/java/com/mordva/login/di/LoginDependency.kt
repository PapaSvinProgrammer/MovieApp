package com.mordva.login.di

import io.ktor.client.HttpClient

interface LoginDependency {
    val client: HttpClient
}