package com.angular.globalretail.core.data.dataaccess

import com.angular.globalretail.core.data.model.SynchronizerData
import com.angular.globalretail.core.data.model.TableData

interface ISynchronizeRepository {
    suspend fun getTables(synchronizerData: SynchronizerData) : TableData
}