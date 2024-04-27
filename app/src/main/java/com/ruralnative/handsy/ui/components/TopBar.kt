package com.ruralnative.handsy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            BarContent()
        },
        modifier = Modifier
            .fillMaxWidth(),
        windowInsets = WindowInsets.statusBars,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}

@Composable
fun BarContent() {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.mascot),
            modifier = Modifier
                .width(36.dp)
                .height(36.dp),
            contentDescription = null
        )
        Spacer(
            modifier = Modifier.width(12.dp)
        )
        Text(
            text = "HANDSY",
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = NunitoFontFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    }
}