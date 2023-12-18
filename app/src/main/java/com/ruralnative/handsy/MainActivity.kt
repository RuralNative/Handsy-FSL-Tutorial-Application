package com.ruralnative.handsy

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HandsyTheme {
            ShowMascot()
            ShowAppName()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreen() {
    CreateDisplayIcon()
}