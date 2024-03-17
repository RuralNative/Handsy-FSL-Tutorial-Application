package com.ruralnative.handsy.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily
import com.ruralnative.handsy.ui.viewmodel.UserIntroViewModel

@Preview
@Composable
fun DevsIntroScreen(
) {
    //onNavigateToMainScreen: () -> Unit
    TODO("Integrate all Composables")
    HeaderText()
    DevsIntroImage()
}

@Preview
@Composable
private fun HeaderText(
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.author_message_one),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = stringResource(R.string.author_message_two),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily
        )
    }
}

@Preview
@Composable
private fun DevsIntroImage() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.author_message_media),
            contentDescription = null,
            modifier = Modifier
                .size(270.dp)
        )
    }
}