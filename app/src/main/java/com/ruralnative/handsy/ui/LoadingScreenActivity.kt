package com.ruralnative.handsy.ui

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ruralnative.handsy.ui.theme.HandsyTheme

class LoadingScreenActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandsyTheme {
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
