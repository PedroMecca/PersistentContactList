package br.edu.ifsp.scl.sc303500x.persistentcontactlist.navigation

sealed class Screen(val route: String) {
    data object List : Screen("list_screen")
    data object Contact : Screen("contact_screen")
}