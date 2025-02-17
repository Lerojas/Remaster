package com.angular.globalretail.ui.base

import com.angular.globalretail.core.domain.APIErrorViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MviViewModel<STATE : BaseViewState<*>, EVENT> : MvvmViewModel() {

    private val _uiState = MutableStateFlow<BaseViewState<*>>(BaseViewState.Empty)
    val uiState = _uiState.asStateFlow()

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.emit(state)
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.value = BaseViewState.Loading
    }

    override fun handleError(apiErrorViewData: APIErrorViewData) {
        super.handleError(apiErrorViewData)
        _uiState.value = BaseViewState.Error(errorViewData = apiErrorViewData)
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        _uiState.value = BaseViewState.Error(error = exception)
    }
}