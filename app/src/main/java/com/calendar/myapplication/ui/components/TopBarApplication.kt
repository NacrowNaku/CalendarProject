package com.calendar.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.calendar.myapplication.R
import com.calendar.myapplication.ui.theme.Purple40

@Composable
fun TopBarComposable(
    userName: String, //could be a User model to hold name and avatar photo
    title: String,
    onBackPressed: () -> Unit
){
    Row(
        Modifier.background(color = Purple40).fillMaxWidth().height(60.dp).padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = userName,
            color = Color.White
        )
        Text(
            text = title,
            color = Color.White
        )
        Image(
            modifier = Modifier.clickable {
                onBackPressed()
            },
            painter = painterResource(R.drawable.back_icon),
            contentDescription = "back",
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

@Preview
@Composable
fun TopBarComposablePreview(){
    TopBarComposable(
        userName = "test",
        title = "testTitle(1)",
        onBackPressed = {}
    )
}