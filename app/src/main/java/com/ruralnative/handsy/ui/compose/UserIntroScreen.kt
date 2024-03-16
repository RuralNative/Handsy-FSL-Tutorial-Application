package com.ruralnative.handsy.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.NunitoFontFamily
import com.ruralnative.handsy.ui.viewmodel.UserIntroViewModel

@Preview
@Composable
fun UserIntroScreen(
) {
    /*
    modifier: Modifier,
    navigateToMainScreen: () -> Unit,
    viewModel: UserIntroViewModel = hiltViewModel()
    */
    /*
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userNameState = uiState.userNameState
     */

    HandsyTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .background(color = MaterialTheme.colorScheme.background)
        ){
            ConstraintLayout {
                val (headerContainer, mascotContainer, inputContainer) = createRefs()

                HeaderText(
                    Modifier.constrainAs(headerContainer) {
                        start.linkTo(parent.start, margin = 50.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(mascotContainer.top)
                    }
                )
                MascotIcon(
                    Modifier
                        .constrainAs(mascotContainer) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .fillMaxWidth()
                )
                /*
                NameInputField(
                    value = userNameState,
                    onValueChange = { newValue ->
                        viewModel.updateUserNameState(newValue)
                    },
                    onDone = {
                        viewModel.saveUserNameInDatabase(it)
                        navigateToMainScreen()
                             },
                    Modifier
                        .constrainAs(inputContainer) {
                            start.linkTo(parent.start)
                            top.linkTo(mascotContainer.bottom)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                )
                 */
            }
        }
    }
}

@Composable
private fun HeaderText(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.intro_greeting_header),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily
        )
        Text(
            text = stringResource(R.string.intro_greeting_subtitle),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 45.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
    }
}

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
                text = stringResource(R.string.name_input_label),
                color = MaterialTheme.colorScheme.primary,
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Bold
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