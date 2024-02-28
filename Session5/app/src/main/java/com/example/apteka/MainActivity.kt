package com.example.apteka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apteka.screens.AddMedicine
import com.example.apteka.screens.MedicineScreen
import com.example.apteka.screens.MedicinesScreen
import com.example.apteka.ui.theme.AptekaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AptekaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    var navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "medicines") {
                        composable("medicines") {
                            MedicinesScreen(navController = navController, applicationContext)
                        }
                        composable("post_medicine") {
                            AddMedicine()
                        }
                        composable("medicine/{id}",
                            arguments = listOf(navArgument("id"){
                                type = NavType.IntType
                            })) {
                            MedicineScreen(it.arguments?.getInt("id")?:3)
                        }
                    }
                }
            }
        }
    }
}
