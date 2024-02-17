package com.ruralnative.handsy.ui.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.HandsyTheme

@Composable
fun MainScreen(
) {
    val context = LocalContext.current
    HandsyTheme {
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {

        }
    }
}

@Composable
fun LessonCard(
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
                onClick = { /*TODO*/ }
            ),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
       Column(
           modifier = Modifier
               .padding(15.dp)
       ) {
           Image(
               modifier = Modifier
                   .aspectRatio(16f /9f),
               painter = painterResource(id = R.drawable.mascot),
               contentDescription = "Lesson"
           )
           Text(
               text = "Lesson 1"
           )
       }
    }
}

@Composable
fun LessonColumn() {

}