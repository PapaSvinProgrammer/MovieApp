package com.example.network.core

enum class NetworkError: Error {
    NO_INTERNET,
    CLIENT_ERROR,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN
}