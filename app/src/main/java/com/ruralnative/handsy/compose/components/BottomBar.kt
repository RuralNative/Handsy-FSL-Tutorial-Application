package com.ruralnative.handsy.compose.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruralnative.handsy.R

@Preview
@Composable
fun BottomBar(
) {
    var selectedItem by remember {mutableIntStateOf(2)}

    NavigationBar(
        modifier = Modifier
            .height(80.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.White,
        tonalElevation = 3.dp,
        windowInsets = WindowInsets.navigationBars,
    ) {
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = { LessonIcon(modifier = Modifier) },
            enabled = true,
            label = { Text(text = "Lessons") },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
        )
        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            icon = { HomeIcon(modifier = Modifier) },
            enabled = true,
            label = { Text(text = "Home") },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
        )
        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 },
            icon = { CameraIcon(modifier = Modifier) },
            enabled = true,
            label = { Text(text = "Camera") },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
        )
    }
}

@Composable
private fun HomeIcon(
    modifier: Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.icon_home),
        contentDescription = "Navigation Button for Home Screen",
        modifier = modifier,
        tint = Color.Unspecified
    )
}

@Composable
private fun LessonIcon(
    modifier: Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.icon_lesson),
        contentDescription = "Navigation Button for Lessons Screen",
        modifier = modifier,
        tint = Color.Unspecified
    )
}

@Composable
private fun CameraIcon(
    modifier: Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.icon_camera),
        contentDescription = "Navigation Button for Camera Screen",
        modifier = modifier,
        tint = Color.Unspecified
    )
}