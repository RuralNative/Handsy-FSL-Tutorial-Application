package com.ruralnative.handsy.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val user = uiState.userName

    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ) {
        val (image, text) = createRefs()
        Image(
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(12.dp),
            painter = painterResource(id = R.drawable.mascot),
            contentDescription = null
        )
        TextContent(
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    top.linkTo(image.bottom, margin = 24.dp)
                    end.linkTo(parent.end)
                },
            user
        )
    }
}

@Composable
private fun TextContent(
    modifier: Modifier = Modifier,
    userName: String
) {
    Row(modifier = modifier) {
        Text(
            text = "Welcome, ",
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontFamily = NunitoFontFamily,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = userName,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily,
            style = MaterialTheme.typography.titleLarge
        )
    }
}