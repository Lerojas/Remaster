package com.angular.globalretail.core.domain

import com.angular.globalretail.core.data.repository.ConfigRepository
import com.angular.globalretail.core.utils.DataState
import com.angular.globalretail.core.utils.apiCall
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetConfigUseCase @Inject constructor(
    private val configMapper: ConfigMapper,
    private val configRepository: ConfigRepository
): DataStateUseCase<Unit, ConfigViewData?>() {

    override suspend fun FlowCollector<DataState<ConfigViewData?>>.execute(params: Unit) {
        emit(
            apiCall(mapper = configMapper){
                configRepository.getConfig()
            }
        )
    }
}