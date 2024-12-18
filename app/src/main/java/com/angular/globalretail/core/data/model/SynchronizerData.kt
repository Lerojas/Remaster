package com.angular.globalretail.core.data.model

import com.google.gson.annotations.SerializedName

data class SynchronizerData (

    @field:SerializedName("empresa")
    val company: String? = null,

    @field:SerializedName("token_sesion")
    val tokenSession: String? = null,

    @field:SerializedName("parametros")
    val parameters: String? = null,

    @field:SerializedName("version_apk")
    val apkVersion: String? = null,

    @field:SerializedName("id_usuario")
    val userId: String? = null,

    @field:SerializedName("id_sucursal")
    val localId: String? = null,

    @field:SerializedName("latitud_usuario")
    val userLatitude: String? = null,

    @field:SerializedName("longitud_usuario")
    val userLongitude: String? = null,
 )