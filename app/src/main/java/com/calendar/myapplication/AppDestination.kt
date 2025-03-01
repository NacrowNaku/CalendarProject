package com.calendar.myapplication

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    val route: String,
    val isFixedButton: Boolean
) {
    CASHIER(R.string.cashier, Icons.Filled.AccountBox, "cashier", false),
    SCHEDULER(R.string.scheduler, Icons.Filled.DateRange, "scheduler", false),
    TICKET(R.string.ticket, Icons.Filled.Email, "ticket", false),
    NOTIFICATION(R.string.notification, Icons.Filled.Notifications, "notification", true),
    SETTING(R.string.setting, Icons.Filled.Settings, "settings", true)
}