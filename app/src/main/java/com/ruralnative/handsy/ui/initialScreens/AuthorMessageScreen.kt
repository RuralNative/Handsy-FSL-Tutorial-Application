package com.ruralnative.handsy.ui.initialScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.BackgroundColor
import com.ruralnative.handsy.ui.theme.NunitoFontFamily
import com.ruralnative.handsy.ui.theme.RegularColor

@Composable
private fun MascotIcon(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.mascot_official),
            contentDescription = "Cat Mascot Image for Handsy",
            modifier = Modifier
                .size(270.dp)
        )
    }
}

@Composable
private fun IntroMessage(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.author_message_one),
            color = RegularColor,
            fontSize = 20.sp,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = stringResource(R.string.author_message_two),
            color = RegularColor,
            fontSize = 20.sp,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun AcceptButton(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                print("Hi")
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = RegularColor,
                contentColor = BackgroundColor
            ),
            content = {
                Text(
                    text = stringResource(R.string.start_journey_button_label),
                    fontSize = 20.sp,
                    fontFamily = NunitoFontFamily,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        )
    }
}

@Preview
@Composable
private fun ScreenContent() {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        val (mascotContainer, messageContainer, buttonContainer) = createRefs()

        MascotIcon(
            Modifier.constrainAs(mascotContainer) {
                start.linkTo(parent.start)
                top.linkTo(parent.top, margin = 100.dp)
                end.linkTo(parent.end)
            }
        )

        IntroMessage(
            Modifier
                .constrainAs(messageContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(mascotContainer.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(buttonContainer.top)
                }
                .fillMaxWidth()
                .height(270.dp)
                .padding(start = 45.dp, end = 45.dp)
        )

        AcceptButton(
            Modifier
                .constrainAs(buttonContainer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 60.dp)
                }
                .padding(start = 45.dp, end = 45.dp)
                .fillMaxWidth()
        )
    }
}
