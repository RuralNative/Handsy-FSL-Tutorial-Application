package com.ruralnative.handsy.ui

import  android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate() EXECUTED")
        setContent {
            val navController: NavController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "entry_screen"
            ) {

            }
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
