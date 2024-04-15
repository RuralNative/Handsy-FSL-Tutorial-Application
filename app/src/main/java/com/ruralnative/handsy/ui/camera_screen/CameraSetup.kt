package com.ruralnative.handsy.ui.camera_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ruralnative.handsy.R
import com.ruralnative.handsy.ui.NunitoFontFamily

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun CameraSetup(
    viewModel: CameraPermissionViewModel = hiltViewModel(),
    onNavigateToDefaultScreen: () -> Unit,
    onNavigateToRationaleScreen: () -> Unit,
    onNavigateToCameraScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ScreenContent()

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    LaunchedEffect(cameraPermissionState) {
        when (cameraPermissionState.status) {
            is PermissionStatus.Granted -> {
                viewModel.setCameraPermissionKey(2)
            }
            is PermissionStatus.Denied -> {
                if (cameraPermissionState.status.shouldShowRationale) {
                    viewModel.setCameraPermissionKey(1)
                } else {
                    viewModel.setCameraPermissionKey(0)
                }
            }
        }
        viewModel.initializeCameraScreen(
            onNavigateToDefaultScreen,
            onNavigateToRationaleScreen,
            onNavigateToCameraScreen
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenContent() {
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
                painter = painterResource(id = R.drawable.mascot),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.camera_permission_init),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = NunitoFontFamily
            )
        }
    }
}