package com.angular.globalretail.ui.splash

import com.angular.globalretail.core.domain.ConfigViewData

data class SplashViewState (
    val configViewData: ConfigViewData? = null
)

internal fun SplashViewState.addConfig(
    configViewData: ConfigViewData?
) = this.copy(
    configViewData = configViewData
)