package com.angular.globalretail.ui.base

import com.angular.globalretail.core.domain.APIErrorViewData

sealed interface BaseViewState<out T> {

    object Loading : BaseViewState<Nothing>

    object Empty : BaseViewState<Nothing>

    data class Data<T>(val value: T) : BaseViewState<T>

    data class Error(
        val errorViewData: APIErrorViewData? = null,
        val error: Throwable? = null
    ) : BaseViewState<Nothing>


}