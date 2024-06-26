package com.ruralnative.handsy.ui.entry

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily

/**
 * EntryScreen is the main entry point of the application.
 * It checks the user count from the database and decides to which screen to navigate to after.
 * If there is no user, it will navigate to the UserIntro screen.
 * If there is at least one, it will navigate to the Home screen.
 * Either navigation must pop this screen from the backstack to disable users in navigating to this screen
 * if system back button is pressed.
 *
 * @param viewModel The ViewModel for the EntryScreen.
 * @param onNavigateToUserIntro A lambda function to navigate to the user introduction screen.
 * @param onNavigateToMain A lambda function to navigate to the main screen.
 */
@Composable
fun EntryScreen(
    viewModel: EntryViewModel,
    onNavigateToUserIntro: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    EntryScreenUI()
    LaunchedEffect(Unit) {
        viewModel.checkUserCountAndNavigate(
            navigateToUserIntro = {
                onNavigateToUserIntro()
            },
            navigateToHome = {
                onNavigateToMain()
            }
        )
    }
}

/**
 * EntryScreenUI is the UI component for the EntryScreen.
 * It displays the application's header text.
 *
 * @param modifier Modifier to apply to the UI component.
 */
@Preview(showBackground = true)
@Composable
private fun EntryScreenUI(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    BackHandler (enabled = true) {
        // Renders System Back Button unusable for the user during Screen duration
    }

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        val container = createRef()
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
                .constrainAs(container) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(Modifier)
        }
    }
}

/**
 * HeaderText displays the application's header text.
 *
 * @param modifier Modifier to apply to the Text component.
 * @param fontColor The color of the font.
 */
@Composable
private fun HeaderText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.handsy_capitalized),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 45.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = NunitoFontFamily
            )
        )
        Text(
            text = stringResource(R.string.fsl_sentence_case),
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 17.7.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily
            )
        )
    }
}