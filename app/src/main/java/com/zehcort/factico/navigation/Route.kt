package com.zehcort.factico.navigation

sealed class Route(val route: String) {
    data object Home : Route("Home")
}