package com.calendar.myapplication.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.calendar.myapplication.AppDestinations
import com.calendar.myapplication.ui.theme.Purple40
import com.calendar.myapplication.ui.theme.grey40

const val ICON_SIZE = 30
const val TEXT_SIZE = 15
const val INDICATOR_SIZE = 40

@Composable
fun NavigationRailColumn(
    modifier: Modifier = Modifier,
    selectedScreen: AppDestinations? = null,
    onClick: (AppDestinations) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.verticalScroll(
                state = rememberScrollState()
            ).weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AppDestinations.entries.filter { !it.isFixedButton }.forEach { screen ->
                ScrollableNavigationButton(
                    isSelected = selectedScreen == screen,
                    screen = screen
                ) {
                    onClick(screen)
                }
            }
        }
        Column(
            modifier = Modifier.padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AppDestinations.entries.filter { it.isFixedButton }.forEach { screen ->
                FixedNavigationButton(
                    isSelected = selectedScreen == screen,
                    screen = screen
                ) {
                    onClick(screen)
                }
            }
        }
    }
}

@Composable
fun ScrollableNavigationButton(
    isSelected: Boolean,
    screen: AppDestinations,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(INDICATOR_SIZE.dp)
                .background(
                    color = if (isSelected) Purple40 else Color.White, RoundedCornerShape(
                        ICON_SIZE.dp
                    )
                )
        ) {
            Image(
                modifier = Modifier
                    .size(ICON_SIZE.dp)
                    .align(Alignment.Center),
                imageVector = screen.icon,
                contentDescription = stringResource(screen.label),
                colorFilter = ColorFilter.tint(
                    if (isSelected) Color.White
                    else grey40
                )
            )
        }
        Text(
            stringResource(screen.label),
            style = TextStyle(
                fontSize = TEXT_SIZE.sp,
                color = grey40
            )
        )
    }
}

@Composable
fun FixedNavigationButton(
    isSelected: Boolean,
    screen: AppDestinations,
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .size(ICON_SIZE.dp)
            .clickable { onClick() },
        imageVector = screen.icon,
        contentDescription = stringResource(screen.label),
        colorFilter = ColorFilter.tint(
            if (isSelected) Purple40
            else grey40
        )
    )
}

@Preview
@Composable
fun NavigationButtonPreview() {
    ScrollableNavigationButton(true, AppDestinations.CASHIER, onClick = {})
}

@Preview
@Composable
fun NavigationRailColumnPreview() {
    NavigationRailColumn(Modifier, AppDestinations.CASHIER) {}
}