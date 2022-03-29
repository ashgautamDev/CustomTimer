package com.ashish.custometimer.navigation

sealed class Screens(val route : String){

    object Onboarding : Screens("onboarding")
    object Main : Screens("main")
    object Add : Screens("add")
    object Start: Screens("start")
    object Timer: Screens("timer")

}
