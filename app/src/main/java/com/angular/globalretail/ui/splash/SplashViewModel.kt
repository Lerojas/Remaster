package com.angular.globalretail.ui.splash

import com.angular.globalretail.core.domain.GetConfigUseCase
import com.angular.globalretail.ui.base.BaseViewState
import com.angular.globalretail.ui.base.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getConfigUseCase: GetConfigUseCase
): MviViewModel<BaseViewState<SplashViewState>, SplashEvent>() {

    private var splashViewState = SplashViewState()

    init {
        onTriggerEvent(SplashEvent.GetConfig)
    }

    override fun onTriggerEvent(eventType: SplashEvent) {
        when(eventType){
            is SplashEvent.GetConfig -> getConfig()
        }
    }

    private fun getConfig() = safeLaunch {
        execute(getConfigUseCase(Unit)){
            splashViewState = splashViewState.addConfig(it)
            setState(BaseViewState.Data(splashViewState))
        }
    }
}