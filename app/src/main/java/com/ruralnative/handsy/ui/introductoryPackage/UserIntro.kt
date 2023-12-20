package com.ruralnative.handsy.ui.introductoryPackage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.CreateWelcomeScreen
import com.ruralnative.handsy.ui.theme.BackgroundColor
import com.ruralnative.handsy.ui.theme.HandsyTheme
import com.ruralnative.handsy.ui.theme.NunitoFontFamily
import com.ruralnative.handsy.ui.theme.RegularColor

class UserIntro : ComponentActivity() {
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
fun ShowHeaderText() {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Hi from",
            color = RegularColor,
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily
        )
        Text(
            text = "Handsy",
            color = RegularColor,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
    }
}

@Composable
fun ShowMascot() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.mascot_official),
            contentDescription = "Cat Mascot Image for Handsy",
            modifier = Modifier
                .size(170.dp)
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun ShowUserInput() {
    var text by rememberSaveable { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = {text = it},
        label = { Text("Label")},
        singleLine = true
    )
}

@Composable
fun ConstructScreenContent() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (headerContainer, mascotContainer, inputContainer) = createRefs()
        Column(
            modifier = Modifier.constrainAs(headerContainer) {
                start.linkTo(parent.start, margin = 16.dp)
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            ShowHeaderText()
        }
    }
}

@Preview
@Composable
fun ConstructUserIntroScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ShowMascot()
        ShowUserInput()
    }
}
