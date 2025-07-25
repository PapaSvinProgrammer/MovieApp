package com.example.utils.error

sealed class NetworkErrorException: Exception()

class NoInternetException: NetworkErrorException()
class ModelSerializationException: NetworkErrorException()
class UnknownException: NetworkErrorException()
class ClientException: NetworkErrorException()
class ServerException: NetworkErrorException()