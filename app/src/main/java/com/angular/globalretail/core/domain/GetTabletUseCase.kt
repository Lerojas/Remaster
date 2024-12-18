package com.angular.globalretail.core.domain

import com.angular.globalretail.core.data.model.SynchronizerData
import com.angular.globalretail.core.data.model.TableData
import com.angular.globalretail.core.data.repository.SynchronizeRepository
import com.angular.globalretail.core.utils.DataState
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetTabletUseCase @Inject constructor(
    private val synchronizeRepository: SynchronizeRepository
): DataStateUseCase<Unit, TableData?>() {

    override suspend fun FlowCollector<DataState<TableData?>>.execute(params: Unit) {
        val data = SynchronizerData()
        val table = synchronizeRepository.getTables(data)


    }
}