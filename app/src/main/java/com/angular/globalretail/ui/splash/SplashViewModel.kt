package com.angular.globalretail.ui.splash

import com.angular.globalretail.core.domain.GetTabletUseCase
import com.angular.globalretail.ui.base.BaseViewState
import com.angular.globalretail.ui.base.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getTabletUseCase: GetTabletUseCase
): MviViewModel<BaseViewState<SplashViewState>, SplashEvent>() {

    override fun onTriggerEvent(eventType: SplashEvent) {
        when(eventType){
            is SplashEvent.GetTables -> getTables()
        }
    }

    private fun getTables() = safeLaunch {
        execute(getTabletUseCase(Unit)){

        }
    }
}