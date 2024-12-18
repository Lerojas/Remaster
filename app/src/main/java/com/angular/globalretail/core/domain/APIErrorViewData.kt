package com.angular.globalretail.core.domain

import java.io.Serializable
import java.net.HttpURLConnection

data class APIErrorViewData (
    var errorTitle: String? = "Error",
    var errorMessage: String? = null,
    var errorCode: String? = null,
    var realHttpStatusCode: Int? = HttpURLConnection.HTTP_INTERNAL_ERROR,
    var showTryAgain: Boolean? = null,
    var showClose: Boolean? = null

) : Serializable
