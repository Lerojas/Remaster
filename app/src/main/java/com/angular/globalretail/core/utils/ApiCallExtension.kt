package com.angular.globalretail.core.utils

import com.angular.globalretail.core.domain.Mapper

suspend fun <T : Any, K : Any> apiCall(mapper: Mapper<T?, K?>, call: suspend () -> K): DataState<T?> {
    return try {
        val response = call()
        val viewData = mapper.executeMapping(response)
        return DataState.Success(viewData)
    } catch (ex: Throwable) {
        DataState.Error(ex.handleAPIErrorResponse())
    }
}


suspend fun <T : Any> apiCall(call: suspend () -> T): DataState<T> {
    return try {
        val response = call()
        DataState.Success(response)
    } catch (ex: Throwable) {
        DataState.Error(ex.handleAPIErrorResponse())
    }
}