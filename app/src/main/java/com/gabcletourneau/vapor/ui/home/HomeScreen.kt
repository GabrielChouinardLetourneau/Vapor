package com.gabcletourneau.vapor.ui.home

import android.provider.MediaStore
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.gabcletourneau.vapor.ui.components.VaporCard
import com.gabcletourneau.vapor.ui.navigation.Destinations
import org.w3c.dom.Text

@Composable
fun HomeScreen(navController: NavHostController) {
    LazyColumn {
        items(
            count = Destinations.entries.size
        ) { index ->
            val destination = Destinations.entries[index]

            VaporCard(navController, destination)
        }
    }

}