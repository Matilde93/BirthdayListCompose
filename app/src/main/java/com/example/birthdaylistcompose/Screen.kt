package com.example.birthdaylistcompose

sealed class Screen(val route: String) {
    object ListScreen : Screen("main_screen")
    object AddScreen : Screen("add_screen")
    object DetailScreen : Screen("detail_screen")
    object LoginScreen : Screen("login_screen")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}