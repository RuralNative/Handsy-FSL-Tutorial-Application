package com.ruralnative.handsy.ui.mainScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.HandsyTheme
import com.ruralnative.handsy.ui.theme.NunitoFontFamily

lateinit var mainNavigation: () -> Unit
lateinit var lessonNavigation: (id: Int) -> Unit
lateinit var cameraNavigation: () -> Unit
lateinit var statsNavigation: () -> Unit

@Composable
fun MainScreen(
    navigateToMainScreen: () -> Unit,
    navigateToLessonScreen: (id: Int) -> Unit,
    navigateToCameraScreen: () -> Unit,
    navigateToStatsScreen: () -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lessons = uiState.alphabetLessons

    mainNavigation = navigateToMainScreen
    lessonNavigation = navigateToLessonScreen
    cameraNavigation = navigateToCameraScreen
    statsNavigation = navigateToStatsScreen

    HandsyTheme {
        Scaffold (
            topBar = {TopBar(modifier = Modifier)},
            bottomBar = { BottomBar(modifier = Modifier)},
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

@Composable
private fun BottomBar(
    modifier: Modifier
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        contentPadding = PaddingValues(5.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize(1F),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = lessonNavigation ) {
                Image(
                    modifier = Modifier
                        .size(120.dp),
                    painter = painterResource(id = R.drawable.icon_lessons),
                    contentDescription = "Lessons Icon"
                )
            }
            IconButton(onClick = cameraNavigation) {
                Image(
                    modifier = Modifier
                        .size(120.dp),
                    painter = painterResource(id = R.drawable.icon_camera),
                    contentDescription = "Camera Icon"
                )
            }
            IconButton(onClick = statsNavigation) {
                Image(
                    modifier = Modifier
                        .size(120.dp),
                    painter = painterResource(id = R.drawable.icon_stats),
                    contentDescription = "User Stats Icon"
                )
            }
        }
    }
}

@Composable
private fun LessonCardList(
    modifier: Modifier,
    lessonList: List<LessonCardState>
) {
    LazyColumn {
        Log.d("LessonList", "LazyColumn INITIALIZED")
        items(lessonList) {lesson ->
            LessonCard(modifier = Modifier, lesson = lesson)
        }
    }
}

@Composable
private fun LessonCard(
    modifier: Modifier,
    lesson: LessonCardState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Lesson Card",
                onClick = lessonNavigation(lesson.lessonID)
            ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
       Row(
           modifier = Modifier
               .padding(10.dp),
           horizontalArrangement = Arrangement.SpaceEvenly
       ) {
           Image(
               modifier = Modifier
                   .size(85.dp),
               painter = painterResource(id = R.drawable.mascot_teach_one),
               contentDescription = "Lesson Mascot",
               contentScale = ContentScale.Fit

           )
           LessonDescription(lesson.lessonName)
       }
    }
}

@Composable
private fun LessonDescription(lessonName: String) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Learn Your Alphabet",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = lessonName,
            fontSize = 46.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
    }
}