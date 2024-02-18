package com.ruralnative.handsy.ui.lessonScreen

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        LessonHeader(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            lesson = state.lessonName
        )

        val image = context.resources.getIdentifier(
            state.lessonMediaResource,
            "drawable",
            context.packageName
        )
        LessonImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            imageResource = image,
            imageDescription = state.lessonName)

        val description = context.resources.getIdentifier(
            state.lessonDescription,
            "string",
            context.packageName
        )
        LessonDescription(
            modifier = Modifier
                .fillMaxWidth(),
            descriptionResource = description
        )
    }
}

@Composable
fun LessonHeader(
    modifier: Modifier,
    lesson: String
) {
    Text(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = NunitoFontFamily,
        text = lesson
    )
}

@Composable
fun LessonImage(
    modifier: Modifier,
    @DrawableRes imageResource: Int,
    imageDescription: String
) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Hand Sign for Letter $imageDescription",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )

}

@Composable
fun LessonDescription(
    modifier: Modifier,
    @StringRes descriptionResource: Int
) {
    Text(
        modifier = modifier,
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = NunitoFontFamily,
        text = stringResource(descriptionResource)
    )
}