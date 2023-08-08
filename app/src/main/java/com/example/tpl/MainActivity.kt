package com.example.tpl


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpl.ui.theme.TPLTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TPLTheme {
                val navi = rememberNavController()
                NavHost(navController = navi, startDestination = "splashscreen") {
                    composable("splashscreen") {
                        SplashContent(navController = navi)
                    }
                    composable("mainActivity") {
                        Homescreen(navController = navi)
                    }
                    composable("onboarding"){
                        OnboardScreen()
                    }
                }
            }
        }
    }
}
@Composable
fun boxshape(modifier: Modifier){

    val whiteClr = Color(android.graphics.Color.parseColor("#FFFFFF"))
    
    Box(
        modifier = Modifier
    ) {

        // Draw the orange backdrop with rounded corners (1/3rd of the screen)
        orangeShape(shape = RoundedCornerShape(10))



    }
}

@Composable
fun Homescreen(modifier: Modifier = Modifier, navController: NavController) {

    Column(
        modifier = modifier.fillMaxWidth().padding(bottom = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        boxshape(modifier = modifier.padding(bottom = 100.dp))
        Spacer(Modifier.height(155.dp))





        ContinueButton(
            modifier = modifier.padding(
                start = 100.dp, end = 100.dp,top = 50.dp
            ), navController = navController
        )


    }
}




@Composable
fun orangeShape(shape: Shape){
    val continueButtonColor = Color(android.graphics.Color.parseColor("#EC600D"))
    Box(
        modifier = Modifier
            .size(400.dp)
            .width(100.dp)
            .height(100.dp)
            .clip(shape)
            .background(continueButtonColor)
    ){
        TPLCard()
    }
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .wrapContentSize(androidx.compose.ui.Alignment.Center)) {
//        Box(
//            modifier = Modifier
//                .size(400.dp)
//                .width(100.dp)
//                .height(100.dp)
//                .clip(shape)
//                .background(continueButtonColor)
//        ){
//            TPLCard()
//        }
//    }
}





@Composable
fun TPLCard(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }

    Card(
        modifier = modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 40.dp,
                bottom = 22.dp
            ) // Adjust the horizontal padding as needed
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()// Adjust the corner radius as needed
            .height(IntrinsicSize.Min),


        elevation = CardDefaults.cardElevation(defaultElevation = 30.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(250.dp),
                painter = painterResource(id = R.drawable.tpllogo),
                contentDescription = null
            )

            Text(
                text = stringResource(id = R.string.mobileNumber),
                style = MaterialTheme.typography.labelSmall
            )
            EditNumberField(
                label = R.string.label,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = amountInput,
                onValueChanged = { amountInput = it }
            )
            Spacer(modifier = Modifier.height(50000.dp) )
        }

    }

}





@Composable
fun ContinueButton(modifier: Modifier, navController: NavController) {
    val continueButtonColor = Color(android.graphics.Color.parseColor("#EC600D"))

    Button(
        onClick = {
            navController.navigate("onboarding")
        },
        modifier = Modifier
            .padding(16.dp)
            .size(width = 200.dp, height = 50.dp) // Set the desired size of the button
            .clip(RoundedCornerShape(percent = 50)) // Use 50% to create an oval shape
            .padding(horizontal = 32.dp), // Add horizontal padding
        colors = ButtonDefaults.buttonColors(continueButtonColor)
    ) {
        Text(
            text = "Continue",
            color = Color.White,
            //style = MaterialTheme.typography.displayMedium,
            //modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_small))
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        singleLine = true,
        modifier = modifier,
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun OrangeBox(shape: Shape){
    val continueButtonColor = Color(android.graphics.Color.parseColor("#EC600D"))
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(androidx.compose.ui.Alignment.Center)) {
        Box(
            modifier = Modifier
                .size(500.dp)
                .clip(shape)
                //.background(continueButtonColor)//,
            //TPLCard()
        ){
            TPLCard()
        }
    }
}

//@Composable
//fun OrangeBackdrop(modifier: Modifier = Modifier) {
  //  val continueButtonColor = Color(android.graphics.Color.parseColor("#EC600D"))
    ////Box(
        //modifier = modifier
          //  .fillMaxSize() // Use weight to let the backdrop take one-third of the screen height) // Adjust horizontal padding as needed
            //.clip(RoundedCornerShape(16.dp))
            //.background(continueButtonColor) // Replace with your desired orange color
    //) {
 //Homescreen()
   // }
//}




@Preview
@Composable
fun HomescreenPreview(){
    TPLTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ){
        //Homescreen(navController = navi)
    }}
}








