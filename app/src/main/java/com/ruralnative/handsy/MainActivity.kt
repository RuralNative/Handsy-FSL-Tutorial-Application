package com.ruralnative.handsy

import  android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ruralnative.handsy.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate() EXECUTED")
        enableEdgeToEdge()

        setContent {
            navHostController = rememberNavController()
            NavGraph(navController = navHostController)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "OnStart() EXECUTED")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "OnRestart() EXECUTED")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop() EXECUTED")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume() EXECUTED")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause() EXECUTED")
    }
}
