package com.example.catalift_tech

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catalift_tech.ui.theme.CataliftTechTheme
import kotlin.jvm.java


val LocalNavHostController = compositionLocalOf<NavHostController> { error("error during navigation") }


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CataliftTechTheme {
                val navHostController = rememberNavController()
                CompositionLocalProvider(LocalNavHostController provides rememberNavController()) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 0.dp),
                        topBar = {
                        },
                        bottomBar = {

                        }
                    ) { innerpadding ->
                        AppNavigation()
                    }
                }
            }
        }
    }
}
const val firstScreen = "firstScreen"
const val secondScreen = "secondScreen"
const val detailsScreen = "detailsScreen"

@Composable
fun AppNavigation() {

    val navigator = LocalNavHostController.current

    NavHost(navController = navigator, startDestination = firstScreen) {
        composable(route = "firstScreen") {
            FirstScreen()
        }
        composable(route = "secondScreen/{selectedProfession}/{selectedCompany}",
            arguments = listOf(
                navArgument("selectedProfession") { type = NavType.StringType },
                navArgument("selectedCompany") { type = NavType.StringType }
            )
        ) {backStackEntry ->
            val selectedProfession = backStackEntry.arguments?.getString("selectedProfession") ?: ""
            val selectedCompany = backStackEntry.arguments?.getString("selectedCompany") ?: ""


            SecondScreen(selectedProfession,selectedCompany)
        }
    }
}



//@Composable
//fun SecondScreen(profession: String,company : String) {
////    val navigator = LocalNavHostController.current
//    val context = LocalContext.current
//
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text("Second Screen")
//            Spacer(modifier = Modifier.height(12.dp))
//            Button(
//                onClick = {
//                    val intent =Intent(context,Details::class.java)
//                    intent.putExtra("profession", profession)
//                    intent.putExtra("company", company)
//                    context.startActivity(intent)
//                }
//            ) {
//                Text("Go back to First Screen")
//            }
//        }
//    }
//}

