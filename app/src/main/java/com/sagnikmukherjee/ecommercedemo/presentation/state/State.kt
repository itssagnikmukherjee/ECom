package com.sagnikmukherjee.ecommercedemo.presentation.state

sealed class ResultState<out T>{
    data class Success<out T>(val data: T): ResultState<T>()
    data class Error(val message: String): ResultState<Nothing>()
    object Loading: ResultState<Nothing>()
}