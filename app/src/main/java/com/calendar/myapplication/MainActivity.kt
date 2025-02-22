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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
        var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.CASHIER) }
        val navigationItemColors = NavigationSuiteDefaults.itemColors(
            navigationBarItemColors = NavigationBarItemDefaults.colors(
                indicatorColor = Purple40,
                selectedIconColor = Color.White,
                selectedTextColor = grey40,
                unselectedIconColor = grey40,
                unselectedTextColor = grey40
            )
        )
        Scaffold(
            topBar = {

            },
            floatingActionButton = {

            }
        ) { contentPadding -> Box(
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
                            selected = screen == currentDestination,
                            onClick = {
                                currentDestination = screen
                            },
                            colors = navigationItemColors
                        )
                    }
                },
                navigationSuiteColors = NavigationSuiteDefaults.colors(
                    navigationBarContainerColor = Color.White,
                ),
                containerColor = Color.White
            ) {
                when (currentDestination) {
                    AppDestinations.CASHIER -> Greeting("Cashier")
                    AppDestinations.SCHEDULER -> Greeting("Scheduler")
                    AppDestinations.TICKET -> Greeting("Ticket")
                }
            }
        }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}