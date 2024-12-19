package com.angular.globalretail.core.data.dataaccess

import com.angular.globalretail.core.data.model.ConfigData

interface IConfigRepository {
    suspend fun getConfig() : ConfigData
}