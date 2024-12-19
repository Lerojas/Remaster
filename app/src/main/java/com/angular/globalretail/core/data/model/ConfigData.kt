package com.angular.globalretail.core.data.model

import com.google.gson.annotations.SerializedName

data class ConfigData (

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("data")
    val data: Data? = null,
)

data class Data (
    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("message")
    val message: String? = null,
)