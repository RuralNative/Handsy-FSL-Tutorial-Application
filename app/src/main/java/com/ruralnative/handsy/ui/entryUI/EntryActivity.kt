package com.ruralnative.handsy.ui.entryUI

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.ruralnative.handsy.ui.theme.HandsyTheme

class EntryActivity : ComponentActivity() {
    private lateinit var viewModel: EntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[EntryViewModel::class.java]

        setContent {
            HandsyTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoadingScreen()
                }
            }
        }

        viewModel.checkUserCountAndNavigate(
            navigateToInitial = navigateForNewUser(),
            navigateToMain =  navigateForCurrentUser()
        )
    }

    private fun navigateForNewUser(): () -> Unit {
        TODO("No User Intro Classes")
    }

    private fun navigateForCurrentUser(): () -> Unit {
        TODO("Not Main Screen Classes")
    }
}
