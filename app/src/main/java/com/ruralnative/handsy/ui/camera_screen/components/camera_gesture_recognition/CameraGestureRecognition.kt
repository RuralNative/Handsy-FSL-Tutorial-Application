package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition

import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.ui.NunitoFontFamily
import java.util.concurrent.Executors

@Composable
fun CameraGestureRecognition(
    viewModel: CameraGestureRecognitionViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val result = uiState.resultName

    val currentContext = LocalContext.current
    val currentConfiguration = LocalConfiguration.current
    val currentLifecycleOwner = LocalLifecycleOwner.current

    val cameraView = remember {
        PreviewView(currentContext)
    }

    LaunchedEffect(Unit) {
        viewModel.setupCameraProvider(
            currentContext,
            currentConfiguration,
            currentLifecycleOwner,
            cameraView
        )
    }


    Box(
        modifier = Modifier
    ) {
        AndroidView(
            factory = {
                cameraView
            },
            modifier = Modifier.fillMaxSize()
        )
        ResultContainer(result)
    }
}

@Composable
private fun ResultContainer(result: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.10f)
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(12.dp))
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(color = MaterialTheme.colorScheme.tertiary),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = result,
                color = MaterialTheme.colorScheme.onTertiary,
                fontFamily = NunitoFontFamily,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall
            )

        }
        Spacer(modifier = Modifier.height(64.dp))
    }
}






