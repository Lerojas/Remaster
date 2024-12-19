package com.angular.globalretail.core.utils

import com.angular.globalretail.core.data.model.APIErrorData
import com.angular.globalretail.core.domain.APIErrorViewData
import com.google.gson.Gson
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.isNotAuthorizationException(): Boolean {
    return this.message?.contains("401").orFalse()
}

fun Throwable.is422Exception(): Boolean {
    return this.message?.contains("422").orFalse()
}

fun APIErrorViewData?.has422ErrorCode(): Boolean {
    return this?.errorCode?.contains("422").orFalse() || this?.realHttpStatusCode == 422
}

fun APIErrorViewData?.isNotFoundException(): Boolean {
    return this?.realHttpStatusCode == 404
}

/**
 * Gets the API error body response
 * (in case is http exception) and
 * transform it to APIErroViewData class
 *
 * @param context           to get possible string resources
 * @param exception         the exception to work on
 * @param defaultErrorMsg   OPTIONAL default error message to override the actual one
 */
fun Throwable?.handleAPIErrorResponse (defaultErrorMsg: String? = null): APIErrorViewData {

    val defaultErrorTitle = "Error"
    val defaultErrorMessage = if (defaultErrorMsg.isNullOrEmpty()) {
        "Error en la comunicación con el servidor"
    } else {
        defaultErrorMsg
    }

    return when (this) {

        is HttpException -> {
            // APIErrorViewData object with default title and message
            var apiErrorViewData: APIErrorViewData? = APIErrorViewData(
                realHttpStatusCode = this.code(),
                errorCode = this.code().toString(),
                errorTitle = defaultErrorTitle,
                errorMessage = defaultErrorMessage
            )

            try {
                this.response()?.let { response ->
                    val parsedError =
                        Gson().fromJson(response.errorBody()?.string(), APIErrorData::class.java)

                    apiErrorViewData?.let {
                        it.realHttpStatusCode = this.code()

                        if (it.errorTitle.isNullOrBlank()) {
                            it.errorTitle = defaultErrorTitle
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return apiErrorViewData!!
        }

        is UnknownHostException -> {
            APIErrorViewData(
                errorTitle = defaultErrorTitle,
                errorMessage = defaultErrorMessage,
                showTryAgain = true,
                showClose = true
            )
        }

        is SocketException -> {

            APIErrorViewData(
                errorTitle = defaultErrorTitle,
                errorMessage = defaultErrorMessage
            )
        }

        is SocketTimeoutException -> {
            APIErrorViewData(
                errorTitle = defaultErrorTitle,
                errorMessage = defaultErrorMessage
            )
        }

        else -> {
            when {
                this?.isNotAuthorizationException() == true -> {
                    // Not authorization exception APIErrorViewData should notify to caller in onAPIError
                    APIErrorViewData(
                        errorCode = "401",
                        errorTitle = defaultErrorTitle,
                        errorMessage = defaultErrorMessage
                    )
                }

                else -> {
                    //Default flow
                    this?.printStackTrace()

                    APIErrorViewData(
                        errorTitle = defaultErrorTitle,
                        errorMessage = defaultErrorMessage
                    )
                }
            }
        }
    }
}