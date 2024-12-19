package com.angular.globalretail.core.data.repository

import com.angular.globalretail.core.data.dataaccess.IConfigRepository
import com.angular.globalretail.core.data.model.ConfigData
import com.angular.globalretail.core.data.network.ApiService
import javax.inject.Inject

class ConfigRepository @Inject constructor(
    private val apiService: ApiService
) : IConfigRepository {

    override suspend fun getConfig(): ConfigData {
        return apiService.getConfig()
    }
}