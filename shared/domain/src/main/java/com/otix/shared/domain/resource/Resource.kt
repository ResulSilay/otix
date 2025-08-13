package com.otix.shared.domain.resource

sealed class Resource<out T> {

    data class Loading(val isLoading: Boolean) : Resource<Nothing>()

    data class Success<T>(val data: T) : Resource<T>()

    data class Failure(val message: String) : Resource<Nothing>()
}

fun <T> Resource<T>.onLoading(action: (isLoading: Boolean) -> Unit): Resource<T> {
    if (this is Resource.Loading) action(isLoading)
    return this
}

fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(data)
    return this
}

fun <T> Resource<T>.onFailure(action: (String?) -> Unit): Resource<T> {
    if (this is Resource.Failure) action(message)
    return this
}
