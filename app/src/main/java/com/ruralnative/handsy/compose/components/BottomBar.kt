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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ruralnative.handsy.R
import com.ruralnative.handsy.navigation.BottomNavigation
import com.ruralnative.handsy.navigation.Screen
import com.ruralnative.handsy.ui.NunitoFontFamily

val navigationItems = listOf(
    BottomNavigation(
        title = "Main",
        route = Screen.MainScreen.route,
        selectedIcon = { LessonIcon(modifier = Modifier) },
        unselectedIcon = { LessonIcon(modifier = Modifier) }
    ),
    BottomNavigation(
        title = "Home",
        route = Screen.MainScreen.route,
        selectedIcon = { HomeIcon(modifier = Modifier) },
        unselectedIcon = { HomeIcon(modifier = Modifier) }
    ),
    BottomNavigation(
        title = "Camera",
        route = Screen.CameraScreen.route,
        selectedIcon = { CameraIcon(modifier = Modifier) },
        unselectedIcon = { CameraIcon(modifier = Modifier) }
    )
)

lateinit var mainScreenNavigation: () -> Unit
lateinit var cameraScreenNavigation: () -> Unit

@Composable
fun BottomBar(
    navigateToMainScreen: () -> Unit,
    navigateToCameraScreen: () -> Unit
) {

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(1)
    }

    LaunchedEffect(Unit) {
        mainScreenNavigation = navigateToMainScreen
        cameraScreenNavigation = navigateToCameraScreen
    }

    NavigationBar(
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    if (selectedItemIndex == 0) {
                        mainScreenNavigation()
                    } else if (selectedItemIndex == 1) {
                        mainScreenNavigation()
                    } else if (selectedItemIndex == 2) {
                        cameraScreenNavigation()
                    }
                },
                icon = {
                       if (selectedItemIndex == index) {
                           item.selectedIcon
                       } else  {
                            item.unselectedIcon
                       }
                },
                label = {
                    TextLabel(
                        label = item.title,
                        modifier = Modifier
                    )
                },
                alwaysShowLabel = false
            )
        }
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