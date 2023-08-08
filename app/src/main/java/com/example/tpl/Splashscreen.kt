package com.example.tpl

import  android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tpl.ui.theme.TPLTheme
import kotlinx.coroutines.delay

//class Splashscreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TPLTheme {
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    SplashContent(navController = )
//                }
//            }
//        }
//    }
//}

@Composable
fun SplashContent(navController: NavController) {
    // Add a delay to simulate a splash screen effect
    LaunchedEffect(Unit) {
        delay(2000) // Adjust the delay duration as needed
        // Navigate to the Homescreen after the delay
        navController.navigate("mainActivity") // Make sure to use the correct destination ID from nav_graph.xml
    }

    // Your existing splash screen content
    splashView()
}
@Composable
fun splashView(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#D3D3D3"))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


splashLogo()

    }
}

@Composable
fun splashLogo(modifier: Modifier = Modifier) {
        val logo: Painter = painterResource(id = R.drawable.splashlogo)
        Image(
            painter = logo,
            contentDescription = null,
            modifier = modifier

        )
}

@Preview
@Composable
fun splashPreview() {

        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            splashView()
        }
}




