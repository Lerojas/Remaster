package com.angular.globalretail.core.data.model

import com.google.gson.annotations.SerializedName

data class TableData (

    @field:SerializedName("empresa")
    val company: String? = null,

    @field:SerializedName("token_sesion")
    val tokenSession: String? = null,

    @field:SerializedName("parametros")
    val parameters: String? = null,

)