package com.angular.globalretail.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angular.globalretail.core.domain.APIErrorViewData
import com.angular.globalretail.core.utils.DataState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class MvvmViewModel : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        handleError(APIErrorViewData(errorTitle = exception.message))
    }

    open fun handleError(apiErrorViewData: APIErrorViewData) {}

    open fun handleError(exception: Throwable) {}
    open fun startLoading() {}

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }


    protected suspend fun <T> execute(
        callFlow: Flow<DataState<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .onStart {
                startLoading()
            }
            .catch {
                handleError(APIErrorViewData(it.message))
            }
            .collect { state ->
                when (state) {
                    is DataState.Error -> handleError(state.error)
                    is DataState.Success -> completionHandler.invoke(state.result)
                }
            }
    }

    protected suspend fun <T> executeCall(
        callFlow: Flow<DataState<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow.collect { state ->
            when (state) {
                is DataState.Success -> completionHandler.invoke(state.result)
                else -> {}
            }
        }
    }

    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { handleError(it) }
            .collect {
                completionHandler.invoke(it)
            }
    }

    companion object {
        private const val SAFE_LAUNCH_EXCEPTION = "ViewModel-ExceptionHandler"
    }
}