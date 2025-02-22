package com.calendar.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.calendar.myapplication.ui.screens.CashierScreen
import com.calendar.myapplication.ui.screens.SchedulerScreen
import com.calendar.myapplication.ui.screens.TicketScreen

@Composable
fun AppNavHost(
    modifier:Modifier = Modifier,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = AppDestinations.CASHIER.route,
        modifier = modifier
    ) {
        composable(route = AppDestinations.CASHIER.route) {
            CashierScreen()
        }
        composable(route = AppDestinations.SCHEDULER.route) {
            SchedulerScreen()
        }
        composable(route = AppDestinations.TICKET.route) {
            TicketScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
