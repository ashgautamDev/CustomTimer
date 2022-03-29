package com.ashish.custometimer.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ashish.custometimer.ui.TimerScreen
import com.ashish.custometimer.ui.add.AddScreen
import com.ashish.custometimer.ui.add.AddViewModel
import com.ashish.custometimer.ui.main.MainScreen
import com.ashish.custometimer.ui.main.MainViewModel
import com.ashish.custometimer.ui.onboarding.OnboardingScreen
import com.ashish.custometimer.ui.start.StartScreen

@ExperimentalFoundationApi
@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Onboarding.route) {
        composable(Screens.Onboarding.route){
            OnboardingScreen(navController)
        }
        composable(Screens.Main.route) {

            val viewModel: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            MainScreen(viewModel, navController)
        }
        composable(Screens.Add.route) {
            val viewModel: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            val addViewModel: AddViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            AddScreen(viewModel, addViewModel, navController)
        }
        composable(
            "${Screens.Start.route}/{tId}",
            arguments = listOf(navArgument("tId") {
                type = NavType.StringType
            })
        ) {
            val viewModel: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            StartScreen(viewModel, navController, it.arguments?.getString("tId") ?: "")


        }

        composable(
            "${Screens.Timer.route}/{sId}",
            arguments = listOf(navArgument("sId") {
                type = NavType.StringType
            })
        ) {
            val viewModel: MainViewModel = viewModel(
                factory = HiltViewModelFactory(LocalContext.current, it)
            )
            val s = it.arguments?.getString("sId")
            if (s != null) {
                TimerScreen(viewModel, navController, s)
            }


        }

    }

}