package com.example.kavyakanaja.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.kavyakanaja.ui.theme.screens.*

@Composable
fun AppNav() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(navController)
        }
        composable(
            route = "detail/{poemId}"
        ) { backStackEntry ->

            val poemId = backStackEntry.arguments
                ?.getString("poemId")
                ?.toIntOrNull() ?: 0

            PoemDetailScreen(
                poemId = poemId,
                navController = navController
            )
        }

        composable("favourites") {
            FavouriteScreen(navController)
        }

        composable("poet_corner") {
            PoetCornerScreen()
        }

        composable("poets") {
            PoetCornerScreen()
        }
    }
}