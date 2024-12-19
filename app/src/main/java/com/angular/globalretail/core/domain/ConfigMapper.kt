package com.angular.globalretail.core.domain

import com.angular.globalretail.core.data.model.ConfigData
import javax.inject.Inject

class ConfigMapper @Inject constructor(
) : Mapper<ConfigViewData?, ConfigData?> {

    override fun executeMapping(type: ConfigData?): ConfigViewData? {
        return type?.let {
            ConfigViewData(
                title = it.data?.title,
                message = it.data?.message
            )
        }
    }
}