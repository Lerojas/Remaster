package com.angular.globalretail.ui.splash

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.angular.globalretail.core.utils.cast
import com.angular.globalretail.ui.base.BaseViewState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen() {
    Scaffold {
        SplashComponent()
    }
}

@Composable
fun SplashComponent(
    viewModel: SplashViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is BaseViewState.Data -> {
            val data = uiState.cast<BaseViewState.Data<SplashViewState>>().value

            data.configViewData?.let {
                Toast.makeText(
                    context,
                    "API success: ${it.title} - ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        is BaseViewState.Loading -> {}
        is BaseViewState.Empty -> {}
        is BaseViewState.Error -> {
            val apiErrorViewData = uiState.cast<BaseViewState.Error>().errorViewData
            Toast.makeText(
                context,
                "${apiErrorViewData?.errorTitle} - ${apiErrorViewData?.errorMessage}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}