package com.ruralnative.handsy.ui.entry

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.NunitoFontFamily

@Composable
fun EntryScreen(
    viewModel: EntryViewModel = hiltViewModel(),
    onNavigateToUserIntro: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    HandsyTheme {
        EntryScreenUI()
        LaunchedEffect(Unit) {
            viewModel.checkUserCountAndNavigate(
                navigateToInitial = {
                    onNavigateToUserIntro()
                },
                navigateToMain = {
                    onNavigateToMain()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EntryScreenUI(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val backgroundColor = Color(ContextCompat.getColor(context, R.color.primary_light))
    val headerColor = Color(ContextCompat.getColor(context, R.color.white))

    BackHandler (enabled = true) {
        // Renders System Back Button unusable for the user during Screen duration
    }

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        val container = createRef()
        Column(
            modifier = Modifier
                .background(color = backgroundColor)
                .constrainAs(container) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(Modifier, headerColor)
        }
    }
}


@Composable
private fun HeaderText(
    modifier: Modifier = Modifier,
    fontColor: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.handsy_capitalized),
            color = fontColor,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 45.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = NunitoFontFamily
            )
        )
        Text(
            text = stringResource(R.string.fsl_sentence_case),
            color = fontColor,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 17.7.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily
            )
        )
    }
}