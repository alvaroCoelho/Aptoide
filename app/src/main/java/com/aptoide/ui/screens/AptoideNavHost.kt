package com.aptoide.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aptoide.data.app.model.AppModel
import com.aptoide.ui.screens.Routers.APP
import com.aptoide.ui.screens.Routers.DETAIL_APP_SCREEN
import com.aptoide.ui.screens.Routers.LIST_APPS_SCREEN

@Composable
fun AptoideNavHost (
    navController: NavHostController = rememberNavController(),
    startDestination: String = LIST_APPS_SCREEN

) {

    NavHost(navController = navController,
        startDestination = startDestination
    ){
        composable(LIST_APPS_SCREEN){
            ListAppsScreen(navController = navController)
        }
        composable(DETAIL_APP_SCREEN){
            val app = navController
                .previousBackStackEntry?.savedStateHandle?.get<AppModel>(APP)
            app?.let {
                DetailAppScreen(it)
            }
        }
    }
}


object Routers{
    const val LIST_APPS_SCREEN = "LIST_APPS_SCREEN"
    const val DETAIL_APP_SCREEN = "DETAIL_APP_SCREEN"
    const val APP = "APP"
}
