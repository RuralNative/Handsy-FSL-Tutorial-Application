package com.ruralnative.handsy.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily
import com.ruralnative.handsy.ui.viewmodel.UserIntroViewModel

@Preview
@Composable
fun DevsIntroScreen(
) {
    //onNavigateToMainScreen: () -> Unit

    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .safeContentPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (headerContainer, imageContainer) = createRefs()
        HeaderText(
            modifier = Modifier
                .constrainAs(headerContainer) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(imageContainer.top)
                }
                .padding(16.dp)
        )
        DevsIntroImage(
            modifier = Modifier
                .constrainAs(imageContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Composable
private fun HeaderText(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.author_message_one),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = stringResource(R.string.author_message_two),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily
        )
    }
}

@Composable
private fun DevsIntroImage(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.author_message_media),
            contentDescription = null,
            modifier = Modifier
                .size(360.dp)
        )
    }
}