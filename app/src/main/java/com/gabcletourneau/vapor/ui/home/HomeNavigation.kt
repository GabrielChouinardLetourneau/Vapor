//package com.gabcletourneau.vapor.ui.home
//
//import androidx.compose.animation.AnimatedContentScope
//import androidx.compose.animation.AnimatedContentTransitionScope
//import androidx.compose.animation.EnterTransition
//import androidx.compose.animation.ExitTransition
//import androidx.compose.animation.core.spring
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.navigation.NamedNavArgument
//import androidx.navigation.NavBackStackEntry
//import androidx.navigation.NavDeepLink
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.compose.composable
//
//
///**
// * This function was inspired by one of the official android jetpack compose samples
// * see: https://github.com/android/compose-samples/blob/dafa1a63262c5ab754a85412456d9d7587f60836/Jetsnack/app/src/main/java/com/example/jetsnack/ui/home/Home.kt
// * */
//fun NavGraphBuilder.composableWithCompositionLocal(
//    route: String,
//    arguments: List<NamedNavArgument> = emptyList(),
//    deepLinks: List<NavDeepLink> = emptyList(),
//    enterTransition: (
//    @JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
//    )? = {
//        fadeIn(spring(
//            dampingRatio = 1f,
//            stiffness = 1600f,
//        ))
//    },
//    exitTransition: (
//    @JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
//    )? = {
//        fadeOut(spring(
//            dampingRatio = 1f,
//            stiffness = 1600f,
//        ))
//    },
//    popEnterTransition: (
//    @JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
//    )? =
//        enterTransition,
//    popExitTransition: (
//    @JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?
//    )? =
//        exitTransition,
//    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
//) {
//    composable(
//        route,
//        arguments,
//        deepLinks,
//        enterTransition,
//        exitTransition,
//        popEnterTransition,
//        popExitTransition,
//    ) {
//        CompositionLocalProvider(
//            LocalNavAnimatedVisibilityScope provides this@composable,
//        ) {
//            content(it)
//        }
//    }
//}