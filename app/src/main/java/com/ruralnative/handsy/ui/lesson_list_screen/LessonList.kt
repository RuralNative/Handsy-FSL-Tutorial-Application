package com.ruralnative.handsy.ui.lesson_list_screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily

/**
 * Displays a LazyColumn of clickable Cards containing description of Lesson.
 * Clicking a Card navigates with a passed ID integer to LessonScreen based from its LessonCardState ID.
 * @param modifier modifier used for LazyColumn
 * @param lessonHeader header description for the lesson
 * @param lessonList list of LessonCardState to display in LazyColumn
 * @param onLessonCardClicked lambda expression used by Card to navigate to Lesson screen
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LessonCardList(
    modifier: Modifier,
    lessonHeader: String,
    lessonList: List<LessonCardState>,
    onLessonCardClicked: (id: Int) -> Unit
) {
    val state: LazyListState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        flingBehavior = rememberSnapFlingBehavior(state)
    ) {
        items(lessonList, key = {lesson -> lesson.lessonID}) {lesson ->
            LessonCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .animateItemPlacement(
                        tween(durationMillis = 150)
                    )
                    .clickable(
                        enabled = true,
                        onClick = { onLessonCardClicked(lesson.lessonID) }
                    ),
                lessonHeader,
                lesson
            )
        }
    }
}

/**
 * Displays a single Lesson as a Card composable
 * @param modifier modifier used for the Card composable
 * @param lessonHeader header description for display
 * @param lesson fetched lesson to display
 */
@Composable
private fun LessonCard(
    modifier: Modifier,
    lessonHeader: String,
    lesson: LessonCardState
) {
    ElevatedCard(
        modifier = modifier,
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
            LessonDescription(
                modifier = Modifier,
                lessonHeader,
                lesson.lessonName
            )
        }
    }
}

/**
 * Displays the description of a lesson as a Column of two Text composables.
 * @param modifier modifier for the Column container
 * @param lessonHeader header description for the lesson
 * @param lessonName name of the lesson
 */
@Composable
private fun LessonDescription(
    modifier: Modifier,
    lessonHeader: String,
    lessonName: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = lessonHeader,
            color = MaterialTheme.colorScheme.primary,
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