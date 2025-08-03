package com.gabcletourneau.vapor.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.gabcletourneau.vapor.ui.battery.BatteryScreen
import com.gabcletourneau.vapor.ui.home.HomeScreen
import com.gabcletourneau.vapor.ui.network.NetworkScreen
import com.gabcletourneau.vapor.ui.sensors.SensorsScreen
import kotlinx.serialization.Serializable


/**
 * Destinations for the navigation
 */
enum class Destinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME("home", "Home", Icons.Default.Home, "Home"),
    NETWORK("network", "Network", Icons.Default.NetworkCheck, "Network"),
    BATTERY("battery", "Battery", Icons.Default.BatteryAlert, "Battery"),
    SENSORS("sensors", "Sensors", Icons.Default.Sensors, "Sensors")
}

@Composable
fun VaporNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController,
        startDestination = Destinations.HOME.route,
        modifier = modifier
    ) {
        composable(route = Destinations.HOME.route) { HomeScreen(navController) }
        composable(route = Destinations.NETWORK.route) { NetworkScreen() }
        composable(route = Destinations.BATTERY.route) { BatteryScreen() }
        composable(route = Destinations.SENSORS.route) { SensorsScreen() }
    }
}

@Composable
fun rememberVaporNavController(navController: NavHostController = rememberNavController()): NavHostController =
    remember(navController) {
        VaporNavController(navController).navController
    }


/**
 * Navigation logic
 */
@Stable
class VaporNavController(val navController: NavHostController) {

//    fun upPress() {
//        navController.navigateUp()
//    }
//
//    fun navigateToBottomBarRoute(route: String) {
//        if(route != navController.currentDestination?.route) {
//            navController.navigate(route) {
//                launchSingleTop = true
//                restoreState = true
//                // Pop up backstack to the first destination and save state.
//                // This makes going back to the start destination when pressing back
//                popUpTo(findStartDestination(navController.graph).id) {
//                    saveState = true
//                }
//            }
//        }
//    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() = this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}