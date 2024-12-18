package com.angular.globalretail.ui.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel()
) {
    viewModel.onTriggerEvent(SplashEvent.GetTables)
}