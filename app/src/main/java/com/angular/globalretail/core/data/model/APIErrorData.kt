package com.angular.globalretail.core.data.model

import com.google.gson.annotations.SerializedName

data class APIErrorData(
    @field:SerializedName("errorTitle")
    val errorTitle: String? = null,
    @field:SerializedName("errorMessage", alternate = ["errorMsg"])
    val errorMessage: String? = null,
    @field:SerializedName("localisedMessage")
    val localisedMessage: String? = null,
    @field:SerializedName("errorId")
    val errorId: String? = null,
    @field:SerializedName("canRefresh")
    val canRefresh: Boolean? = null,
    @field:SerializedName("errorCode")
    val errorCode: String? = null,
    @field:SerializedName("httpStatusCode")
    val httpStatusCode: String? = null,
    @field:SerializedName("message")
    val message: String? = null
)