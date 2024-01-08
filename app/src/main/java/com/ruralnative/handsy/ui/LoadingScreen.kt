package com.ruralnative.handsy.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.HandsyTheme
import com.ruralnative.handsy.ui.theme.NunitoFontFamily

@Composable
fun LoadingScreen(
    navController: NavController
) {
    HandsyTheme {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            val container = createRef()
            Column(
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
                    MascotIcon()
                    HeaderText()
                }
            }
        }
    }
}

@Composable
private fun MascotIcon() {
    Image(
        painter = painterResource(id = R.drawable.mascot_official),
        contentDescription = stringResource(R.string.mascot_content_description),
        modifier = Modifier
            .size(250.dp)
            .padding(bottom = 16.dp)
    )
}

@Composable
private fun HeaderText() {
    HandsyTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.handsy_capitalized),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = NunitoFontFamily
                )
            )
            Text(
                text = stringResource(R.string.fsl_sentence_case),
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,

                )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    HandsyTheme {
        LoadingScreen(navController = rememberNavController())
    }
}