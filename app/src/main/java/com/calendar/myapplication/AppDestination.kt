package com.calendar.myapplication

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.vector.ImageVector


enum class AppDestinations (
    @StringRes val label: Int,
    val icon: ImageVector
) {
    CASHIER(R.string.cashier, Icons.Filled.AccountBox),
    SCHEDULER(R.string.scheduler, Icons.Filled.DateRange),
    TICKET(R.string.ticket, Icons.Filled.Email)
}