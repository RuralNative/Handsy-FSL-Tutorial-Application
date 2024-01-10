package com.ruralnative.handsy.ui.entryUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.theme.HandsyTheme
import com.ruralnative.handsy.ui.theme.NunitoFontFamily

@Composable
fun EntryScreen() {
    val viewModel: EntryViewModel = viewModel()
    viewModel.checkUserCountAndNavigate(
        navigateToInitial = {
            TODO("Add code for navigating to initial screen")
        },
        navigateToMain = {
            TODO("Add code for navigating to main screen")
        }
    )
    EntryScreenUI(Modifier)
}

@Preview(showBackground = true)
@Composable
private fun EntryScreenUI(modifier: Modifier = Modifier) {
    ConstraintLayout {
        val container = createRef()
        HandsyTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier.constrainAs(container) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom) },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MascotIcon()
                    HeaderText()
                }
            }
        }
    }
}

@Composable
private fun MascotIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.mascot_official),
        contentDescription = stringResource(R.string.mascot_content_description),
        modifier = Modifier
            .size(250.dp)
            .padding(bottom = 16.dp)
    )
}

@Composable
private fun HeaderText(modifier: Modifier = Modifier) {
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