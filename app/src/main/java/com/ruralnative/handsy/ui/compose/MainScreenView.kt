package com.ruralnative.handsy.ui.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.compose.components.BottomBar
import com.ruralnative.handsy.ui.compose.components.TopBar
import com.ruralnative.handsy.ui.state.LessonCardState
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.NunitoFontFamily
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