package com.example.birthdaylistcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.birthdaylistcompose.ListScreenHandler

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val listScreenHandler = ListScreenHandler(navController)
    val addScreenHandler = AddScreenHandler(navController)
    val detailScreenHandler = DetailScreenHandler(navController)
    val loginScreenHandler = LoginScreenHandler(navController)

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.ListScreen.route) {
            listScreenHandler.ListScreen()
        }
        composable(route = Screen.AddScreen.route) {
            addScreenHandler.AddPerson()
        }
        composable(
            route = "${Screen.DetailScreen.route}/{person.id}",
            arguments = listOf(navArgument("person.id") {
                type = NavType.StringType
                nullable = false
            })
        ) { entry ->
            detailScreenHandler.DetailScreen(entry.arguments?.getString("person.id") ?: "")
        }
        composable(route = Screen.LoginScreen.route){
            loginScreenHandler.LoginScreen()
        }
    }
}