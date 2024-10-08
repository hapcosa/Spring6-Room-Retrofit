package com.example.spring6.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.spring6.view.DetailView
import com.example.spring6.view.HomeView
import com.example.spring6.viewModel.telefonoViewModel

@Composable
fun NavManager(viewModel: telefonoViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Home") {
        Log.e("ErrorM","aqui3")
        composable("Home") {
            HomeView(navController, viewModel)
        }
        composable(
            "DetailView/{id}{name}", arguments = listOf(
                navArgument("id") { type = NavType.IntType }, navArgument("name") { type = NavType.StringType })

        ) {
            val id = it.arguments!!.getInt("id")
            val name:String = it.arguments!!.getString("name").toString()
            DetailView(navController, viewModel, id, name)
        }
    }
}