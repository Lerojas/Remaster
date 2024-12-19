package com.angular.globalretail.core.data.network

import com.angular.globalretail.core.data.model.ConfigData
import retrofit2.http.POST

interface ApiService {
    @POST("/api/angular/config.php")
    suspend fun getConfig() : ConfigData
}
