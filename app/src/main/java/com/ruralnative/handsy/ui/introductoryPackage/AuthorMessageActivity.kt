package com.ruralnative.handsy.ui.introductoryPackage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.BackgroundColor
import com.ruralnative.handsy.ui.theme.DarkBackgroundColor
import com.ruralnative.handsy.ui.theme.HandsyTheme
import com.ruralnative.handsy.ui.theme.NunitoFontFamily
import com.ruralnative.handsy.ui.theme.RegularColor

class AuthorMessageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandsyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundColor
                ) {
                }
            }
        }
    }
}

@Composable
private fun ShowMascot(modifier: Modifier) {
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
private fun ShowIntroMessage(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Handsy Sign Language is a mobile application with the purpose of providing an accessible platform for learning the Filipino Sign Language for users of all background, especially the deaf and the mute",
            color = RegularColor,
            fontSize = 20.sp,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "We hope you enjoy using this application",
            color = RegularColor,
            fontSize = 20.sp,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun ShowAcceptButton(modifier: Modifier) {
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
                    text = "Start Your Journey",
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
private fun CreateActivityScreen() {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        val (mascotContainer, messageContainer, buttonContainer) = createRefs()

        ShowMascot(
            Modifier.constrainAs(mascotContainer) {
                start.linkTo(parent.start)
                top.linkTo(parent.top, margin = 100.dp)
                end.linkTo(parent.end)
            }
        )

        ShowIntroMessage(
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

        ShowAcceptButton(
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

