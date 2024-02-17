package com.ruralnative.handsy.ui.mainScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.HandsyTheme
import com.ruralnative.handsy.ui.theme.NunitoFontFamily
import kotlinx.coroutines.delay

@Preview
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lessons = uiState.alphabetLessons

    HandsyTheme {
        Scaffold (
            topBar = {TopBar(modifier = Modifier)},
            bottomBar = { BottomBar(modifier = Modifier)},
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            Log.d("LessonList", "Content INITIALIZED")
            LessonCardList(
                modifier = Modifier
                    .padding(innerPadding),
                lessonList = lessons)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
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
fun BottomBar(
    modifier: Modifier
) {
    NavigationBar(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {

    }
}

@Composable
fun LessonCardList(
    modifier: Modifier,
    lessonList: List<LessonCardState>
) {
    Log.d("LessonList", "LessonCardList START INITIALIZED")
    if (lessonList.isEmpty()) {
        Log.d("LessonList", "LessonCardList is EMPTY")
    } else {
        Log.d("LessonList", "LessonCardList is NOT EMPTY")
    }
    LazyColumn {
        Log.d("LessonList", "LazyColumn INITIALIZED")
        items(lessonList) {lesson ->
            LessonCard(modifier = Modifier, lesson = lesson)
        }
    }
}

@Composable
fun LessonCard(
    modifier: Modifier,
    lesson: LessonCardState
) {
    Log.d("LessonList", "Individual Card of ${lesson.lessonName} INITIALIZED")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Lesson Card",
                onClick = { /*TODO*/ }
            ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
       Row(
           modifier = Modifier
               .padding(15.dp),
           horizontalArrangement = Arrangement.SpaceEvenly
       ) {
           Image(
               painter = painterResource(id = R.drawable.mascot_teach_one),
               contentDescription = "Lesson"
           )
           LessonDescription("Learn Your Alphabet", lesson.lessonName)
       }
    }
}

@Composable
fun LessonDescription(lessonHeader: String, lessonName: String) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = lessonHeader,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = lessonName,
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily
        )
    }
}