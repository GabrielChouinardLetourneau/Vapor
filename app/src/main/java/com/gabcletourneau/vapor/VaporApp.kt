package com.gabcletourneau.vapor

import VaporBottomAppBar
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gabcletourneau.vapor.ui.navigation.VaporNavHost
import com.gabcletourneau.vapor.ui.navigation.rememberVaporNavController
import com.gabcletourneau.vapor.ui.theme.VaporTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun VaporApp() {
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
                )
            }
        }
    }
}
