package com.calendar.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.calendar.myapplication.navigation.NavigationRailColumn
import com.calendar.myapplication.ui.components.TopBarComposable
import com.calendar.myapplication.ui.theme.CalendarProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    CalendarProjectTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = AppDestinations.entries.find { it.route == currentDestination?.route }
            ?: AppDestinations.CASHIER
        Scaffold(
            topBar = {
                TopBarComposable(
                    userName = "test",
                    title = "testTitle(1)",
                    onBackPressed = {}
                )
            },
            floatingActionButton = {

            }
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .padding(contentPadding)
            ) {
                Row {
                    NavigationRailColumn(
                        modifier = Modifier.width(100.dp),
                        selectedScreen = currentScreen
                    ) { screen ->
                        navController.navigateSingleTopTo(screen.route)
                    }
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}