package com.ruralnative.handsy.ui.compose.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.ruralnative.handsy.ui.compose.lessonNavigation
import com.ruralnative.handsy.ui.state.LessonCardState

private operator fun LazyListState.getValue(nothing: Nothing?, property: KProperty<*>): Any {

}

/**
 * Displays a LazyColumn of lessons
 * @param modifier modifier used for LazyColumn
 * @param lessonHeader header description for the lesson
 * @param lessonList list of LessonCardState to display in LazyColumn
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LessonCardList(
    modifier: Modifier,
    lessonHeader: String,
    lessonList: List<LessonCardState>
) {
    val state: LazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyColumnState)

    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = ,
        horizontalAlignment = Alignment.CenterHorizontally,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = ),
        userScrollEnabled: Boolean = true
    ) {
        Log.d("LessonList", "LazyColumn INITIALIZED")
        items(lessonList, key = {lesson -> lesson.lessonID}) {lesson ->
            LessonCard(lessonHeader, lesson)
        }
    }
}

/**
 * Displays a single Lesson as a Card composable
 * @param lesson fetched lesson to display
 */
@Composable
private fun LessonCard(
    lessonHeader: String,
    lesson: LessonCardState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable(
                enabled = true,
                onClickLabel = "Lesson Card",
                onClick = { lessonNavigation(lesson.lessonID) }
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