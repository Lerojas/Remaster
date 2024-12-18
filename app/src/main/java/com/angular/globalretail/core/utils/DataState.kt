package com.angular.globalretail.core.utils

import com.angular.globalretail.core.domain.APIErrorViewData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

sealed class DataState<out T> {
    data class Success<out T>(val result: T) : DataState<T>()
    data class Error(val error: APIErrorViewData) : DataState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$result]"
            is Error -> "Error[exception=$error]"
        }
    }

    inline fun <R : Any> map(transform: (T) -> R): DataState<R> {
        return when (this) {
            is Error -> Error(this.error)
            is DataState.Success -> DataState.Success(transform(this.result))
        }
    }

}

fun <T : Any> Flow<T>.wrapWithStatefulData(): Flow<DataState<T>> = transform { value ->
    return@transform emit(DataState.Success(value))
}


inline fun <T : Any> Flow<DataState<T>>.onSuccessState(crossinline action: suspend (value: T) -> Unit): Flow<DataState<T>> =
    onEach {
        if (it is DataState.Success) action(it.result)
    }