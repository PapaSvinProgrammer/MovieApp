package com.mordva.util.error

sealed class NetworkErrorException(message: String? = null) : Exception(message)

class NoInternetException(message: String? = null) : NetworkErrorException(message)
class ModelSerializationException(message: String? = null) : NetworkErrorException(message)
class UnknownException(message: String? = null) : NetworkErrorException(message)
class ClientException(message: String? = null) : NetworkErrorException(message)
class ServerException(message: String? = null) : NetworkErrorException(message)