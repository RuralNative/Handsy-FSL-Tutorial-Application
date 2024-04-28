package com.ruralnative.handsy.ui.dev_intro

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * DevsIntroScreen is the main entry point for developers.
 * It displays an introduction screen with a header, an image, and a button to start the journey.
 *
 * @param viewModel The ViewModel for the DevsIntroScreen.
 * @param onNavigateToHome A lambda function to be called when the start button is clicked.
 */
@Composable
fun DevsIntroScreen(
    viewModel: DevsIntroViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val headerVisibility = uiState.headerVisibility
    val imageVisibility = uiState.imageVisibility
    val buttonVisibility = uiState.buttonVisibility

    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (headerContainer, imageContainer, buttonContainer) = createRefs()

        LaunchedEffect("animationKey") {
            launch {
                delay(600)
                viewModel.setHeaderVisibility(true)
            }
            launch {
                delay(300)
                viewModel.setImageVisibility(true)
            }
            launch {
                delay(3000)
                viewModel.setButtonVisibility(true)
            }
        }

        HeaderText(
            modifier = Modifier
                .constrainAs(headerContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(imageContainer.top)
                }
                .padding(32.dp),
            visibility = headerVisibility
        )

        DevsIntroImage(
            modifier = Modifier
                .constrainAs(imageContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            visibility = imageVisibility
        )

        MainScreenButton(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(buttonContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(imageContainer.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            visibility = buttonVisibility
        ) { viewModel.navigateToHome(onNavigateToHome) }

    }
}

/**
 * HeaderText displays the header text for composable screen.
 *
 * @param modifier Modifier to apply to the Text component.
 */
@Composable
private fun HeaderText(
    modifier: Modifier,
    visibility: Boolean
) {
    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(animationSpec = tween(300)) + slideInHorizontally(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300)) + slideOutHorizontally(animationSpec = tween(300))
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.author_message_one),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = NunitoFontFamily
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = stringResource(R.string.author_message_two),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = NunitoFontFamily
            )
        }
    }
}

/**
 * DevsIntroImage displays image for the composable screen.
 *
 * @param modifier Modifier to apply to the Image component.
 */
@Composable
private fun DevsIntroImage(
    modifier: Modifier,
    visibility: Boolean
) {
    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(animationSpec = tween(300)) + slideInHorizontally(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300)) + slideOutHorizontally(animationSpec = tween(300))
    ) {
        Box(
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.author_message_media),
                contentDescription = null,
                modifier = Modifier
                    .size(360.dp)
            )
        }
    }
}

/**
 * MainScreenButton is the button that users click to confirm message is read and navigate to HomeScreen.
 *
 * @param modifier Modifier to apply to the ExtendedFloatingActionButton component.
 * @param onButtonClick A lambda function to be called when the button is clicked.
 */
@Composable
private fun MainScreenButton(
    modifier: Modifier,
    visibility: Boolean,
    onButtonClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visibility,
        enter = fadeIn(animationSpec = tween(300)) + slideInHorizontally(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300)) + slideOutHorizontally(animationSpec = tween(300))
    ) {
        ExtendedFloatingActionButton(
            onClick = { onButtonClick() },
            modifier = modifier,
            content = {
                Text(
                    text = "Start Journey",
                    modifier = Modifier
                )
            }
        )
    }
}