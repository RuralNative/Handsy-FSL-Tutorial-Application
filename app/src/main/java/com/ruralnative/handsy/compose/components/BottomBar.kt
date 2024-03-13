package com.ruralnative.handsy.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ruralnative.handsy.R
import com.ruralnative.handsy.compose.cameraNavigation
import com.ruralnative.handsy.compose.mainNavigation
import com.ruralnative.handsy.compose.statsNavigation

@Composable
public fun BottomBar(
    modifier: Modifier
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        contentPadding = PaddingValues(5.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize(1F),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = mainNavigation ) {
                Image(
                    modifier = Modifier
                        .size(120.dp),
                    painter = painterResource(id = R.drawable.icon_lessons),
                    contentDescription = "Lessons Icon"
                )
            }
            IconButton(onClick = cameraNavigation) {
                Image(
                    modifier = Modifier
                        .size(120.dp),
                    painter = painterResource(id = R.drawable.icon_camera),
                    contentDescription = "Camera Icon"
                )
            }
            IconButton(onClick = statsNavigation) {
                Image(
                    modifier = Modifier
                        .size(120.dp),
                    painter = painterResource(id = R.drawable.icon_stats),
                    contentDescription = "User Stats Icon"
                )
            }
        }
    }
}