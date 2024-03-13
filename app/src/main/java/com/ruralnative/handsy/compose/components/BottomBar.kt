package com.ruralnative.handsy.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collection.mutableVectorOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruralnative.handsy.R
import com.ruralnative.handsy.compose.cameraNavigation
import com.ruralnative.handsy.compose.mainNavigation
import com.ruralnative.handsy.compose.statsNavigation

@Preview
@Composable
fun BottomBar(
) {
    var selectedItem by remember {mutableIntStateOf(0)}

    NavigationBar {
        NavigationBarItem(
            selected = ,
            onClick = { /*TODO*/ },
            icon = { /*TODO*/ },
            enabled = true,
            label = { Text(text = "Lessons") },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
        )
        NavigationBarItem(
            selected = ,
            onClick = { /*TODO*/ },
            icon = { /*TODO*/ }
        )
        NavigationBarItem(
            selected = ,
            onClick = { /*TODO*/ },
            icon = { /*TODO*/ }
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