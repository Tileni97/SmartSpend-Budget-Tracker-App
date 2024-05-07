package com.example.smartspend.Screens.screencomponents

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun IntransfereScreen(navController: NavHostController) {
}


@Preview(showBackground = true)
@Composable
fun IntransfereScreenPreview() {
    val navControllerOne = rememberNavController()
    IntransfereScreen(navControllerOne)

}