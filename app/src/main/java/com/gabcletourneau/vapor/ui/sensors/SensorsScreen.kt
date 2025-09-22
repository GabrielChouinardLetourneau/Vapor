package com.gabcletourneau.vapor.ui.sensors

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.gabcletourneau.vapor.ui.components.ButtonType
import com.gabcletourneau.vapor.ui.components.VaporButton
import com.gabcletourneau.vapor.ui.home.HomeScreen
import com.gabcletourneau.vapor.ui.home.HomeScreenViewModel
import com.gabcletourneau.vapor.ui.navigation.rememberVaporNavController
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun SensorsScreenContent(
    viewModel: SensorsScreenViewModel = hiltViewModel()
) {
    SensorsScreen(viewModel)
}

@Composable
fun SensorsScreen(viewModel: SensorsScreenViewModel) {
    val isDark = viewModel.isDark
    val currentLux = viewModel.currentLux
    val relativeHumidity = viewModel.relativeHumidity
    val temp = viewModel.temperature
    val proximity = viewModel.proximity

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {
                // capture click
            },
    ) {
        val lightCondition = if (isDark) "dark" else "bright"


        Text(
            text = "It is $lightCondition right now and the temp is $temp"
        )
    }
}