package com.ruralnative.handsy.ui.user_intro

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.NunitoFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * UserIntroScreen is the entry screen for new users.
 * It displays in an animated manner a greeting, a mascot, and a text field for the user to enter their name.
 *
 * @param viewModel The ViewModel for the UserIntroScreen.
 * @param onNavigateToDevsIntro A lambda function to navigate to the developers introduction screen.
 */
@Composable
fun UserIntroScreen(
    viewModel: UserIntroViewModel,
    onNavigateToDevsIntro: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userNameState = uiState.userNameState

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .imePadding()
    ) {
        val (headerContainer, mascotContainer, inputContainer) = createRefs()
        val headerAnimation = remember { Animatable(0f) }
        val mascotAnimation = remember { Animatable(0f) }
        val textBoxAnimation = remember { Animatable(0f) }

        LaunchedEffect("animationKey") {
            launch {
                delay(600)
                headerAnimation.animateTo(1f, animationSpec = tween(1000))
            }
            launch {
                delay(300)
                mascotAnimation.animateTo(1f, animationSpec = tween(1500))
            }
            launch {
                delay(900)
                textBoxAnimation.animateTo(1f, animationSpec = tween(1000))
            }
        }

        HeaderText(
            Modifier
                .constrainAs(headerContainer) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(mascotContainer.top)
                }
                .padding(start = 16.dp, end = 16.dp)
                .graphicsLayer { alpha = headerAnimation.value }
        )

        MascotIcon(
            Modifier
                .fillMaxWidth()
                .constrainAs(mascotContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .graphicsLayer { alpha = mascotAnimation.value }
        )

        NameInputField(
            value = userNameState,
            onValueChange = { newValue ->
                viewModel.updateUserNameState(newValue)
            },
            onDone = {
                viewModel.saveUserNameInDatabase(it, onNavigateToDevsIntro)
            },
            Modifier
                .height(56.dp)
                .constrainAs(inputContainer) {
                    start.linkTo(parent.start, margin = 32.dp)
                    top.linkTo(mascotContainer.bottom)
                    end.linkTo(parent.end, margin = 32.dp)
                    bottom.linkTo(parent.bottom)
                }
                .background(color = MaterialTheme.colorScheme.surface)
                .graphicsLayer { alpha = textBoxAnimation.value }
        )
    }
}

/**
 * HeaderText displays the greeting text for the UserIntroScreen.
 *
 * @param modifier Modifier to apply to the Text component.
 */
@Composable
private fun HeaderText(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.intro_greeting_header),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
        Text(
            text = stringResource(R.string.intro_greeting_subtitle),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily
        )
    }
}

/**
 * MascotIcon displays the mascot image for the UserIntroScreen.
 *
 * @param modifier Modifier to apply to the Image component.
 */
@Composable
private fun MascotIcon(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.mascot),
            contentDescription = stringResource(id = R.string.mascot_content_description),
            modifier = Modifier
                .size(270.dp)
        )
    }
}

/**
 * NameInputField is a TextField for the user to enter their name.
 * It handles text changes and the "Done" action on the keyboard.
 *
 * @param value The current value of the TextField.
 * @param onValueChange A lambda function to handle text changes.
 * @param onDone A lambda function to handle the "Done" action on the keyboard.
 * @param modifier Modifier to apply to the TextField component.
 */
@Composable
private fun NameInputField(
    value: String,
    onValueChange: (String) -> Unit,
    onDone: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(
                text = stringResource(R.string.name_input_label)
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone(value) }
        ),
        singleLine = true
    )
}