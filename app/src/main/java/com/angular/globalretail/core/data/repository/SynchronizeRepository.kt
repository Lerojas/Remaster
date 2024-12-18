package com.angular.globalretail.core.data.repository

import com.angular.globalretail.core.data.dataaccess.ISynchronizeRepository
import com.angular.globalretail.core.data.model.SynchronizerData
import com.angular.globalretail.core.data.model.TableData
import com.angular.globalretail.core.data.network.ApiService
import javax.inject.Inject

class SynchronizeRepository @Inject constructor(
    private val apiService: ApiService
) : ISynchronizeRepository {

    override suspend fun getTables(synchronizerData: SynchronizerData): TableData {
        return apiService.getTables(synchronizerData)
    }
}