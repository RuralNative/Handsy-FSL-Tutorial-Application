package com.ruralnative.handsy.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.ruralnative.handsy.ui.HandsyTheme

/**
 * A reusable Scaffold component that provides a consistent layout structure for the application.
 *
 * This scaffold includes a top bar, a bottom bar, and a content area that adapts to the safe content window insets.
 * It uses the [HandsyTheme] to apply a consistent theme across the application.
 *
 * @param currentDestination The current navigation destination. Used to determine which navigation item is currently selected.
 * @param onNavigateToLessonListScreen A lambda function to navigate to the Lesson List screen.
 * @param onNavigateToHomeScreen A lambda function to navigate to the Home screen.
 * @param onNavigateToCameraSetupScreen A lambda function to navigate to the Camera Setup screen.
 * @param composableContent A lambda function that defines the content of the scaffold. It receives [PaddingValues]
 * which can be used to adjust the padding of the content according to the scaffold's layout
 * through Modifier.consumeWindowsInset().
 */
@Composable
fun HandsyScaffold(
    currentDestination: NavDestination?,
    onNavigateToLessonListScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToCameraSetupScreen: () -> Unit,
    composableContent: @Composable (PaddingValues) -> Unit
) {
    HandsyTheme {
        Scaffold (
            modifier = Modifier,
            topBar = { TopBar() },
            bottomBar = {
                BottomBar(
                    currentDestination,
                    onNavigateToLessonListScreen,
                    onNavigateToHomeScreen,
                    onNavigateToCameraSetupScreen
                )
            },
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets.safeContent,
            content = { innerPadding ->
                composableContent(innerPadding)
            }
        )
    }
}