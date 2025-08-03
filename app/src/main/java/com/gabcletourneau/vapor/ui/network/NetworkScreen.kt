package com.gabcletourneau.vapor.ui.network

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.gabcletourneau.vapor.ui.components.ButtonType
import com.gabcletourneau.vapor.ui.components.VaporButton

@Composable
fun NetworkScreen() {
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
        Text(
            text = "NETWORK SCREEN"
        )

        VaporButton(onClick = { /*TODO*/ }, type = ButtonType.FILLED) {
            Text("Filled Button")
        }
    }
}