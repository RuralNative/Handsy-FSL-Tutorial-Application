package com.ruralnative.handsy.compose.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.tappableElement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsCompat
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun BottomBar(
) {
    var selectedItem by remember {mutableIntStateOf(2)}

    NavigationBar(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.White,
        tonalElevation = 3.dp,
        windowInsets = WindowInsets.navigationBars
    ) {
        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = { LessonIcon(modifier = Modifier) },
            enabled = true,
            label = { TextLabel(label = "Lesson", modifier = Modifier) },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
        )
        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            icon = { HomeIcon(modifier = Modifier) },
            enabled = true,
            label = { TextLabel(label = "Home", modifier = Modifier) },
            alwaysShowLabel = false,
            colors = NavigationBarItemDefaults.colors(),
        )
        NavigationBarItem(
            selected = selectedItem == 3,
            onClick = { selectedItem = 3 },
            icon = { CameraIcon(modifier = Modifier) },
            enabled = true,
            label = { TextLabel(label = "Camera", modifier = Modifier) },
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
private fun TextLabel(
    label: String,
    modifier: Modifier
) {
    Text(
        text = label,
        modifier = modifier,
        fontSize = 12.sp,
        fontWeight = FontWeight(500),
        fontFamily = NunitoFontFamily,
        lineHeight = 16.sp,
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