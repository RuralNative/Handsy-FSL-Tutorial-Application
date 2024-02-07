package com.ruralnative.handsy.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ruralnative.handsy.ui.theme.HandsyTheme

@Preview
@Composable
fun MainScreen(
) {
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
fun LessonCard() {

}

@Composable
fun LessonColumn() {

}