package com.ruralnative.handsy.ui.assessment_screen

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.NunitoFontFamily

@Composable
fun AssessmentScreen(
    viewModel: AssessmentViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val onCorrectAnswerClicked: () -> Unit = { viewModel.onCorrectButtonClicked(onNavigateToHome) }
    val onIncorrectAnswerClicked: () -> Unit = { viewModel.onIncorrectButtonClicked(onNavigateToHome) }

    HandsyTheme {
        LessonScaffold(
            modifier = Modifier,
            state = uiState,
            context = context,
            onCorrectAnswerClicked,
            onIncorrectAnswerClicked
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier
) {
    TopAppBar(
        modifier = modifier,
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
private fun LessonScaffold(
    modifier: Modifier,
    state: AssessmentState,
    context: Context,
    onCorrectAnswerClicked: () -> Unit,
    onIncorrectAnswerClicked: () -> Unit
) {
    Scaffold (
        modifier = modifier,
        topBar = { TopBar(modifier = Modifier) },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.safeContent
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
        ) {
            LessonColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(24.dp),
                state = state,
                context = context,
                onCorrectAnswerClicked = onCorrectAnswerClicked,
                onIncorrectAnswerClicked = onIncorrectAnswerClicked
            )
        }
    }
}

@Composable
private fun LessonColumn(
    modifier: Modifier,
    state: AssessmentState,
    context: Context,
    onCorrectAnswerClicked: () -> Unit,
    onIncorrectAnswerClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        QuestionHeader(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        val image = context.resources.getIdentifier(
            state.displayedMedia,
            "drawable",
            context.packageName
        )
        QuestionImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.secondary),
            imageResource = image
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        val description = context.resources.getIdentifier(
            state.displayedDescription,
            "string",
            context.packageName
        )
        QuestionDescription(
            modifier = Modifier
                .fillMaxWidth(),
            questionResource = description
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        QuestionButtons(
            modifier = Modifier,
            correctAnswer = state.displayedAnswer,
            onCorrectAnswerClicked = { onCorrectAnswerClicked() },
            onIncorrectAnswerClicked = { onIncorrectAnswerClicked() }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        QuestionResult(
            modifier = Modifier
                .fillMaxWidth(),
            result = state.questionResultDescription
        )

    }
}

@Composable
fun QuestionHeader(
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = NunitoFontFamily,
        text = "What Letter?",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displaySmall
    )
}

@Composable
fun QuestionImage(
    modifier: Modifier,
    @DrawableRes imageResource: Int
) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Hand Sign",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )

}

@Composable
fun QuestionDescription(
    modifier: Modifier,
    @StringRes questionResource: Int
) {
    Text(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onBackground,
        fontWeight = FontWeight.Normal,
        fontFamily = NunitoFontFamily,
        textAlign = TextAlign.Justify,
        text = stringResource(questionResource),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun QuestionButtons(
    modifier: Modifier,
    correctAnswer: String,
    onCorrectAnswerClicked: () -> Unit,
    onIncorrectAnswerClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(0.4f),
            onClick = { onCorrectAnswerClicked() }
        ) {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = NunitoFontFamily,
                text = correctAnswer,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(0.8f),
            onClick = { onIncorrectAnswerClicked() }
        ) {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = NunitoFontFamily,
                text = "D",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun QuestionResult(
    modifier: Modifier,
    result: String
) {
    Text(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = NunitoFontFamily,
        text = result,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displaySmall
    )
}