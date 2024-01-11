package com.ruralnative.handsy.ui

import  android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ruralnative.handsy.ui.theme.HandsyTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate() EXECUTED")
        setContent {
            val navController = rememberNavController()
            NavGraph(navController = navController)
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
