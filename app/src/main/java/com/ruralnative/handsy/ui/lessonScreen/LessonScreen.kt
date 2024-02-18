package com.ruralnative.handsy.ui.lessonScreen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.ui.theme.NunitoFontFamily

@Composable
fun LessonScreen(
    viewModel: LessonViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
}

@Composable
private fun LessonScaffold(
    modifier: Modifier
) {
    Scaffold (
        topBar = { TopBar(modifier = Modifier) },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .padding(top = innerPadding.calculateTopPadding())
            ) {
                TODO("Show Composables for Lesson")
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "Handsy",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = NunitoFontFamily
            )
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}