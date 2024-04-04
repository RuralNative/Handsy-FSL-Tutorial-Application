package com.ruralnative.handsy.ui.main_screen

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ruralnative.handsy.ui.components.BottomBar
import com.ruralnative.handsy.ui.components.TopBar
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.components.LessonCardList

@Composable
fun MainScreen(
    navigateToLessonScreen: (id: Int) -> Unit,
    navigationController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lessons = uiState.alphabetLessons
    HandsyTheme {
        Scaffold (
            modifier = Modifier,
            topBar = { TopBar() },
            bottomBar = {
                BottomBar(
                    navigationController
            ) },
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets.safeContent,
            content = { innerPadding ->
                Surface(
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    LessonCardList(
                        modifier = Modifier
                            .fillMaxSize(),
                        lessonHeader = "Know Your Alphabet",
                        lessonList = lessons,
                        navigateToLessonScreen
                    )
                }
            }
        )
    }
}