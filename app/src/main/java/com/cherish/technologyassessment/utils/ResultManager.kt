package com.cherish.technologyassessment.utils

sealed class ResultManager<out T> {
    data class Loading(val state: Boolean = true): ResultManager<Nothing>()
    data class Failure(val error: Throwable): ResultManager<Nothing>()
    data class Success<T>(val data: T): ResultManager<T>()
}