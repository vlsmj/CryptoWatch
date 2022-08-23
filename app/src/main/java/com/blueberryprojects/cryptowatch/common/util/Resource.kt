package com.blueberryprojects.cryptowatch.common.util

sealed class Resource<T>(val data: T? = null, val errorMessage: UiText? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(errorMessage: UiText?, data: T? = null) : Resource<T>(data, errorMessage)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}