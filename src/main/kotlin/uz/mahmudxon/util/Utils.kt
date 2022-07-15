package uz.mahmudxon.util

sealed class TransActionData<out T : Any> {
    data class Success<out T : Any>(val data: T) : TransActionData<T>()
    data class Error(val error: Exception) : TransActionData<Nothing>()
}