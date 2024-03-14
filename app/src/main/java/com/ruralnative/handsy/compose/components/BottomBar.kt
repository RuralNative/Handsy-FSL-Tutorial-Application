package com.ruralnative.handsy.compose.components

import androidx.annotation.DrawableRes
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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ruralnative.handsy.R
import com.ruralnative.handsy.navigation.BottomNavigation
import com.ruralnative.handsy.navigation.Screen
import com.ruralnative.handsy.ui.NunitoFontFamily



@Composable
fun BottomBar(
    navigationController: NavHostController
) {
    val navController = navigationController
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(1)
    }

    NavigationBar(
    ) {
        val navigationItems = listOf(
            BottomNavigation(
                title = "Lesson",
                route = Screen.MainScreen.route,
                selectedIcon = {
                    SelectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_lesson,
                        contentDescription = "Lesson Navigation Button"
                    )
                },
                unselectedIcon = {
                    UnselectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_lesson,
                        contentDescription = "Lesson Navigation Button"
                    )
                }
            ),
            BottomNavigation(
                title = "Home",
                route = Screen.MainScreen.route,
                selectedIcon = {
                    SelectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_home,
                        contentDescription = "Home Navigation Button"
                    )
                },
                unselectedIcon = {
                    UnselectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_home,
                        contentDescription = "Home Navigation Button"
                    )
                }
            ),
            BottomNavigation(
                title = "Camera",
                route = Screen.CameraScreen.route,
                selectedIcon = {
                    SelectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_camera,
                        contentDescription = "Camera Navigation Button"
                    )
                },
                unselectedIcon = {
                    UnselectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_camera,
                        contentDescription = "Camera Navigation Button"
                    )
                }
            )
        )

        val selectionMap = remember(currentDestination) {
            navigationItems.associateWith { item ->
                currentDestination?.hierarchy?.any { it.route == item.route } == true
            }
        }

        navigationItems.forEach { item ->
            val selected = selectionMap.getOrDefault(item, false)
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(item.route) },
                icon = {
                    if (selected) {
                        item.selectedIcon()
                    } else  {
                        item.unselectedIcon()
                    }
                },
                label = {
                    Text(
                        item.title,
                        fontWeight =
                            if (selected) FontWeight.Bold
                            else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Composable
private fun SelectedIcon(
    modifier: Modifier,
    @DrawableRes resourceID: Int,
    contentDescription: String,
) {
    Icon(
        painter = painterResource(id = resourceID),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = MaterialTheme.colorScheme.secondaryContainer
    )
}

@Composable
private fun UnselectedIcon(
    modifier: Modifier,
    @DrawableRes resourceID: Int,
    contentDescription: String,
) {
    Icon(
        painter = painterResource(id = resourceID),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = MaterialTheme.colorScheme.onSecondaryContainer
    )
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