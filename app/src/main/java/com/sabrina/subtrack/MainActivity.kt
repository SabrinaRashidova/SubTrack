package com.sabrina.subtrack

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sabrina.subtrack.ui.screens.Routes
import com.sabrina.subtrack.ui.screens.ThemeViewModel
import com.sabrina.subtrack.ui.screens.add_subscription.AddSubscriptionScreen
import com.sabrina.subtrack.ui.screens.subscription_list.SubscriptionListScreen
import com.sabrina.subtrack.ui.theme.SubTrackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
           SubTrackTheme(themeViewModel = themeViewModel) {
               val launcher = rememberLauncherForActivityResult(
                   ActivityResultContracts.RequestPermission()
               ) { isGranted ->  }

               LaunchedEffect(Unit) {
                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                       launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
                   }
               }

               val navController = rememberNavController()
               NavHost(navController = navController, startDestination = "list"){
                   composable(Routes.LIST) {
                       SubscriptionListScreen(onNavigateToAdd = {
                           navController.navigate(Routes.ADD)
                       }, themeViewModel = themeViewModel)
                   }
                   composable(Routes.ADD) {
                       AddSubscriptionScreen(onPopBack = {
                           navController.popBackStack()
                       })
                   }
               }
           }
        }
    }
}
