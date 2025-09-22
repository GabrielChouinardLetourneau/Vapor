package com.gabcletourneau.vapor

import VaporBottomAppBar
import android.app.Application
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gabcletourneau.vapor.ui.navigation.VaporNavHost
import com.gabcletourneau.vapor.ui.navigation.rememberVaporNavController
import com.gabcletourneau.vapor.ui.sensors.SensorsScreenViewModel
import com.gabcletourneau.vapor.ui.theme.DarkGrey
import com.gabcletourneau.vapor.ui.theme.VaporTheme
import com.gabcletourneau.vapor.ui.theme.White
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VaporApp: Application()

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun VaporComposeApp() {
    val viewModel = viewModel<SensorsScreenViewModel>()
    val isDark = viewModel.isDark

    VaporTheme {
        SharedTransitionLayout {
            val vaporNavController = rememberVaporNavController()
            Scaffold(
                bottomBar = { VaporBottomAppBar() }
            ) { innerPadding ->
                VaporNavHost(
                    navController = vaporNavController,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(
                            if (isDark) DarkGrey else White
                        )
                )
            }
        }
    }
}
