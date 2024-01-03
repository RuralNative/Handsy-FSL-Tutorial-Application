package com.ruralnative.handsy.ui.initialScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.ruralnative.handsy.ui.theme.DarkBackgroundColor
import com.ruralnative.handsy.ui.theme.NunitoFontFamily
import com.ruralnative.handsy.ui.theme.RegularColor

@Preview
@Composable
public fun UserIntroScreen() {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        val (headerContainer, mascotContainer, inputContainer) = createRefs()

        HeaderText(
            Modifier.constrainAs(headerContainer) {
                start.linkTo(parent.start, margin = 50.dp)
                top.linkTo(parent.top)
                bottom.linkTo(mascotContainer.top)
            }
        )

        MascotIcon(
            Modifier
                .constrainAs(mascotContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
        )

        NameInputField(
            Modifier
                .constrainAs(inputContainer) {
                    start.linkTo(parent.start)
                    top.linkTo(mascotContainer.bottom)
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
            text = stringResource(R.string.intro_greeting),
            color = RegularColor,
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily
        )
        Text(
            text = stringResource(R.string.handsy_normal_case),
            color = RegularColor,
            fontSize = 45.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
    }
}

@Composable
private fun MascotIcon(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.mascot_official),
            contentDescription = stringResource(id = R.string.mascot_content_description),
            modifier = Modifier
                .size(270.dp)
        )
    }
}

@Composable
private fun NameInputField(modifier: Modifier) {
    var userName by rememberSaveable { mutableStateOf("false") }
    TextField(
        value = userName,
        onValueChange = {userName = it},
        modifier = modifier,
        label = {
            Text(
                text = stringResource(R.string.name_input_label),
                color = DarkBackgroundColor,
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.Bold
            )
        },
        singleLine = true
    )
}