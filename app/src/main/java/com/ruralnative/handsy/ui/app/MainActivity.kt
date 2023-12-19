package com.ruralnative.handsy.ui.app

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.HandsyTheme
import com.ruralnative.handsy.ui.theme.NunitoFontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandsyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowAppName()
                }
            }
        }
    }
}

@Composable
fun ShowMascot() {
    Image(
        painter = painterResource(id = R.drawable.mascot_official),
        contentDescription = "Cat Mascot Image for Handsy",
        modifier = Modifier
            .size(170.dp)
            .padding(bottom = 16.dp)
    )
}

@Composable
fun ShowAppName() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "HANDSY",
            textAlign = TextAlign.Center,
            fontFamily = NunitoFontFamily,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "FSL Application",
            textAlign = TextAlign.Center,
            fontFamily = NunitoFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal

        )
    }
}

@Composable
fun CreateDisplayIcon() {
    ConstraintLayout (
        modifier = Modifier.fillMaxSize(),
    ) {
        val container = createRef()
        Column (
            modifier = Modifier.constrainAs(container) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HandsyTheme {
                ShowMascot()
                ShowAppName()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WelcomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

    }
    CreateDisplayIcon()
}