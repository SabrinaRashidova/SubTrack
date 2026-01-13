package com.sabrina.subtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sabrina.subtrack.ui.screens.Routes
import com.sabrina.subtrack.ui.screens.add_subscription.AddSubscriptionScreen
import com.sabrina.subtrack.ui.screens.subscription_list.SubscriptionListScreen
import com.sabrina.subtrack.ui.theme.SubTrackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           SubTrackTheme {
               val navController = rememberNavController()
               NavHost(navController = navController, startDestination = "list"){
                   composable(Routes.LIST) {
                       SubscriptionListScreen(onNavigateToAdd = {
                           navController.navigate("add")
                       })
                   }
                   composable(Routes.ADD) {
                       AddSubscriptionScreen(onPopBack = {
                           navController.popBackStack()
                       })
                   }
               } }
        }
    }
}
