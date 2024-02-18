package com.ruralnative.handsy.ui.lessonScreen

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.ui.theme.HandsyTheme
import com.ruralnative.handsy.ui.theme.NunitoFontFamily

@Composable
fun LessonScreen(
    viewModel: LessonViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    HandsyTheme {
        LessonScaffold(
            modifier = Modifier,
            state = uiState,
            context = context
        )
    }
}

@Composable
private fun LessonScaffold(
    modifier: Modifier,
    state: LessonState,
    context: Context
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
                LessonColumn(
                    modifier = modifier,
                    state = state,
                    context = context
                )
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

@Composable
private fun LessonColumn(
    modifier: Modifier,
    state: LessonState,
    context: Context
) {
    Column(
        modifier = Modifier
    ) {
        lessonHeader(modifier = Modifier, lesson = state.lessonName)

        val image = context.resources.getIdentifier(
            state.lessonMediaResource,
            "drawable",
            context.packageName
        )
        lessonImage(modifier = modifier, imageResource = image, imageDescription = state.lessonName)

        val description = context.resources.getIdentifier(
            state.lessonDescription,
            "string",
            context.packageName
        )
        lessonDescription(modifier = modifier, descriptionResource = description)
    }
}

@Composable
fun lessonHeader(
    modifier: Modifier,
    lesson: String
) {
    Text(
        text = lesson
    )
}

@Composable
fun lessonImage(
    modifier: Modifier,
    @DrawableRes imageResource: Int,
    imageDescription: String
) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Hand Sign for Letter $imageDescription"
    )

}

@Composable
fun lessonDescription(
    modifier: Modifier,
    @StringRes descriptionResource: Int
) {
    Text(
        text = stringResource(descriptionResource)
    )
}