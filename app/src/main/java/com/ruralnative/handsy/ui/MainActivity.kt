package com.ruralnative.handsy.ui

import  android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate() EXECUTED")
        setContent {
            val navHostController = rememberNavController()
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
