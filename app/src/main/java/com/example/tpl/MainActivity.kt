package com.example.tpl

import android.os.Bundle
import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpl.ui.theme.TPLTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TPLTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Homescreen()
                }
            }
        }
    }
}

@Composable
fun Homescreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        TPLCard(modifier = Modifier.weight(1f)) // Use weight to let the card take the required height
        Spacer(modifier = Modifier.height(10.dp)) // Add some spacing between the card and button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                ,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            ContinueButton()
        }
    }
}




@Composable
fun TPLCard(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }

    Card(
        modifier = modifier
            .padding(start = 45.dp, end = 45.dp, top = 50.dp, bottom = 250.dp) // Adjust the horizontal padding as needed
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()// Adjust the corner radius as needed
            .height(0.dp),

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
fun ContinueButton() {
    val continueButtonColor = Color(android.graphics.Color.parseColor("#EC600D"))

    Button(
        onClick = { /* TODO */ },
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



@Preview
@Composable
fun HomescreenPreview(){
    TPLTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ){
        Homescreen()
    }}
}






