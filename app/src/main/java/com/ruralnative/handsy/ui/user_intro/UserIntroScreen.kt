package com.ruralnative.handsy.ui.user_intro

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.R
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
    val supportingTextState = uiState.isNameIncompatible
    val headerVisibility = uiState.headerVisibility
    val imageVisibility = uiState.imageVisibility
    val textFieldVisibility = uiState.textFieldVisibility

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
            .imePadding()
    ) {
        val (headerContainer, mascotContainer, inputContainer) = createRefs()

        LaunchedEffect("animationKey") {
            launch {
                delay(600)
                viewModel.setHeaderVisibilty(true)
            }
            launch {
                delay(300)
                viewModel.setImageVisibility(true)
            }
            launch {
                delay(900)
                viewModel.setTextFieldVisibility(true)
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
                .padding(start = 16.dp, end = 16.dp),
            headerVisibility
        )

        MascotIcon(
            Modifier
                .fillMaxWidth()
                .constrainAs(mascotContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            imageVisibility
        )

        NameInputField(
            Modifier
                .constrainAs(inputContainer) {
                    start.linkTo(parent.start, margin = 32.dp)
                    top.linkTo(mascotContainer.bottom)
                    end.linkTo(parent.end, margin = 32.dp)
                    bottom.linkTo(parent.bottom)
                },
            textFieldVisibility,
            value = userNameState,
            onValueChange = { newValue ->
                viewModel.updateUserNameState(newValue)
            },
            onDone = {
                viewModel.saveUserNameInDatabase(it, onNavigateToDevsIntro)
            },
            { SupportingText(isError = supportingTextState) }
        )
    }
}


@Composable
private fun HeaderText(
    modifier: Modifier,
    visibility: Boolean
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn(animationSpec = tween(1000)) + slideInHorizontally(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)) + slideOutHorizontally(animationSpec = tween(1000))
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(R.string.intro_greeting_header),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = NunitoFontFamily
                )
                Text(
                    text = stringResource(R.string.intro_greeting_subtitle),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = NunitoFontFamily
                )
            }
        }
    }

}

@Composable
private fun MascotIcon(
    modifier: Modifier,
    visibility: Boolean
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn(animationSpec = tween(1000)) + slideInHorizontally(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)) + slideOutHorizontally(animationSpec = tween(1000))
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
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
    }
}

@Composable
private fun NameInputField(
    modifier: Modifier,
    visibility: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    onDone: (String) -> Unit,
    supportingText: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn(animationSpec = tween(1000)) + slideInHorizontally(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)) + slideOutHorizontally(animationSpec = tween(1000))
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.height(72.dp),
                label = {
                    Text(
                        text = stringResource(R.string.name_input_label)
                    )
                },
                supportingText = supportingText,
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
    }
}

@Composable
private fun SupportingText(isError: Boolean) {
    if (!isError) {
        Text(
            text = stringResource(R.string.supporting_text_error),
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily,
            style = MaterialTheme.typography.bodySmall
        )
    } else {
        Text(
            text = stringResource(R.string.supporting_text_neutral),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily,
            style = MaterialTheme.typography.bodySmall
        )
    }
}