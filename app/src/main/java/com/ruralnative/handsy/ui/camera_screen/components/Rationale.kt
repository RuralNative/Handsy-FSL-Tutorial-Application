package com.ruralnative.handsy.ui.camera_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
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
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.NunitoFontFamily

@Preview(showBackground = true)
@Composable
private fun Rationale() {
    HandsyTheme {
        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            val container = createRef()
            Column(
                modifier = Modifier
                    .constrainAs(container) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .padding(12.dp),
                    painter = painterResource(id = R.drawable.mascot),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(12.dp),
                    text = stringResource(R.string.camera_permission_header),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = NunitoFontFamily
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(12.dp),
                    text = stringResource(R.string.camera_permission_description_one),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = NunitoFontFamily
                )
                ElevatedButton(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(12.dp),
                        text = stringResource(R.string.camera_permission_description_one),
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = NunitoFontFamily
                    )
                }
            }
        }
    }
}