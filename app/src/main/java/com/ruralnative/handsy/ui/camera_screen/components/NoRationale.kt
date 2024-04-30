package com.ruralnative.handsy.ui.camera_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.NunitoFontFamily

/**
 * A composable function that displays a UI for CameraSetup when no rationale is needed for requesting camera permission.
 *
 * @param buttonFunction A lambda function to be called when the user clicks the request permission button.
 */
@Composable
fun NoRationale(
    buttonFunction: () -> Unit
) {
    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ) {
        val (image, text, button) = createRefs()
        Image(
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(text.top)
                }
                .size(240.dp)
                .padding(12.dp),
            painter = painterResource(id = R.drawable.mascot),
            contentDescription = null
        )
        TextContent(
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, margin = 48.dp)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        RequestPermissionButton(
            modifier = Modifier
                .constrainAs(button) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                },
            onClick = { buttonFunction() }
        )
    }
}

@Composable
private fun TextContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(start = 24.dp, end = 24.dp),
            text = stringResource(R.string.camera_permission_header),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = NunitoFontFamily,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(start = 24.dp, end = 24.dp),
            text = stringResource(R.string.camera_permission_description_three),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Normal,
            fontFamily = NunitoFontFamily,
            softWrap = true,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun RequestPermissionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(modifier = modifier) {
        ElevatedButton(
            onClick = { onClick() }
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(12.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(R.string.camera_permission_button),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontFamily = NunitoFontFamily,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
