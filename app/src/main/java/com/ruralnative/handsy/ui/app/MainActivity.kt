package com.ruralnative.handsy.ui.app

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.HandsyTheme

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
        contentDescription = "Cat Mascot Image for Handsy"
    )
}

@Composable
fun ShowAppName(modifier: Modifier = Modifier) {
    Column() {
        Text(
            text = "HANDSY",
            modifier = modifier
        )
        Text(
            text = "FSL Application",
            modifier = modifier
        )
    }
}

@Composable
fun CreateDisplayIcon() {
    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ) {
        val container = createRef()

        Column (
            modifier = Modifier.constrainAs(container) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
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