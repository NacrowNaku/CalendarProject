package com.calendar.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.calendar.myapplication.ui.components.TopBarComposable
import com.calendar.myapplication.ui.theme.CalendarProjectTheme
import com.calendar.myapplication.ui.theme.Purple40
import com.calendar.myapplication.ui.theme.grey40

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
        val navigationItemColors = NavigationSuiteDefaults.itemColors(
            navigationBarItemColors = NavigationBarItemDefaults.colors(
                indicatorColor = Purple40,
                selectedIconColor = Color.White,
                selectedTextColor = grey40,
                unselectedIconColor = grey40,
                unselectedTextColor = grey40
            )
        )
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
                modifier = Modifier.padding(contentPadding)
            ) {
                NavigationSuiteScaffold(
                    navigationSuiteItems = {
                        AppDestinations.entries.forEach { screen ->
                            item(
                                icon = {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = stringResource(screen.label),
                                    )
                                },
                                label = {
                                    Text(stringResource(screen.label))
                                },
                                selected = screen == currentScreen,
                                onClick = {
                                    navController.navigateSingleTopTo(screen.route)
                                },
                                colors = navigationItemColors
                            )
                        }
                    },
                    layoutType = NavigationSuiteType.NavigationRail,
                    navigationSuiteColors = NavigationSuiteDefaults.colors(
                        navigationRailContainerColor = Color.White,
                    ),
                    containerColor = Color.White
                ) {
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}