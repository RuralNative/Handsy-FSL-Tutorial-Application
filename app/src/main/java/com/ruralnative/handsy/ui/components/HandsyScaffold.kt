package com.ruralnative.handsy.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.ruralnative.handsy.ui.HandsyTheme

/**
 * A reusable Scaffold component that provides a consistent layout structure for the application.
 *
 * This scaffold includes a top bar, a bottom bar, and a content area that adapts to the safe content window insets.
 * It uses the [HandsyTheme] to apply a consistent theme across the application.
 *
 * @param navigationController The [NavHostController] used for navigation within the application.
 * @param composableContent A lambda function that defines the content of the scaffold. It receives [PaddingValues]
 * which can be used to adjust the padding of the content according to the scaffold's layout
 * through Modifier.consumeWindowsInset()
 *
 */
@Composable
fun HandsyScaffold(
    currentDestination: NavDestination?,
    composableContent: @Composable (PaddingValues) -> Unit
) {
    HandsyTheme {
        Scaffold (
            modifier = Modifier,
            topBar = { TopBar() },
            bottomBar = { BottomBar(currentDestination) },
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets.safeContent,
            content = { innerPadding ->
                composableContent(innerPadding)
            }
        )
    }
}