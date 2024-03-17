package com.ruralnative.handsy.ui.compose

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
import com.ruralnative.handsy.ui.compose.components.BottomBar
import com.ruralnative.handsy.ui.compose.components.TopBar
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.compose.components.LessonCardList
import com.ruralnative.handsy.ui.viewmodel.MainScreenViewModel

lateinit var mainNavigation: () -> Unit
lateinit var lessonNavigation: (id: Int) -> Unit
lateinit var cameraNavigation: () -> Unit
lateinit var statsNavigation: () -> Unit

@Composable
fun MainScreen(
    navigateToLessonScreen: (id: Int) -> Unit,
    navigationController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lessons = uiState.alphabetLessons

    lessonNavigation = navigateToLessonScreen

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
                        lessonList = lessons
                    )
                }
            }
        )
    }
}