package com.ruralnative.handsy.ui

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ruralnative.handsy.ui.theme.HandsyTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandsyTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
