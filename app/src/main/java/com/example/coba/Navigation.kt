package com.example.coba

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coba.model.AuthViewModel
import com.example.coba.pages.AddPage
import com.example.coba.pages.HomePage
import com.example.coba.pages.SearchPage
import com.example.coba.pages.SigninPage
import com.example.coba.pages.SignupPage
import com.example.coba.pages.UpdatePage


@Composable
fun Navigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "signin", builder = {
        composable("signin") {
            SigninPage(modifier, navController, authViewModel)
        }
        composable("signup") {
            SignupPage(modifier, navController, authViewModel)
        }
        composable("home") {
            HomePage(modifier, navController, authViewModel)
        }
        composable("add") {
            AddPage(modifier, navController)
        }
        composable("update/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            UpdatePage(modifier, navController, taskId)
        }
        composable("search") {
            SearchPage(modifier, navController)
        }
    })
}