package com.ruralnative.handsy.ui.dev_intro

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
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
    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {

        val (headerContainer, imageContainer, buttonContainer) = createRefs()
        val headerAnimation = remember { Animatable(0f) }
        val imageAnimation = remember { Animatable(0f) }
        val buttonAnimation = remember { Animatable(0f) }

        LaunchedEffect("animationKey") {
            launch {
                delay(600)
                headerAnimation.animateTo(1f, animationSpec = tween(1000))
            }
            launch {
                delay(300)
                imageAnimation.animateTo(1f, animationSpec = tween(1500))
            }
            launch {
                delay(3000)
                buttonAnimation.animateTo(1f, animationSpec = tween(1000))
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
                .padding(32.dp)
                .graphicsLayer { alpha = headerAnimation.value }
        )

        DevsIntroImage(
            modifier = Modifier
                .constrainAs(imageContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .graphicsLayer { alpha = imageAnimation.value }
        )

        MainScreenButton(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(buttonContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(imageContainer.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .graphicsLayer { alpha = buttonAnimation.value }
        ) { viewModel.navigateToHome(onNavigateToHome) }

    }
}

/**
 * HeaderText displays the header text for composable screen.
 *
 * @param modifier Modifier to apply to the Text component.
 */
@Composable
private fun HeaderText(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.author_message_one),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = stringResource(R.string.author_message_two),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily
        )
    }
}

/**
 * DevsIntroImage displays image for the composable screen.
 *
 * @param modifier Modifier to apply to the Image component.
 */
@Composable
private fun DevsIntroImage(modifier: Modifier) {
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

/**
 * MainScreenButton is the button that users click to confirm message is read and navigate to HomeScreen.
 *
 * @param modifier Modifier to apply to the ExtendedFloatingActionButton component.
 * @param onButtonClick A lambda function to be called when the button is clicked.
 */
@Composable
private fun MainScreenButton(
    modifier: Modifier,
    onButtonClick: () -> Unit
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