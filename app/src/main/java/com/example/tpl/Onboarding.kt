package com.example.tpl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tpl.ui.theme.Montserrat
import com.example.tpl.ui.theme.TPLTheme
import kotlinx.coroutines.delay
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.sign


class Onboarding : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TPLTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    OnboardScreen()
                }
            }
        }
    }
}

@Composable
fun OnboardScreen() {
    val cardCount = 5 // Set the total number of cards here
    var currentCard by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#EC600D")))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Logo(modifier = Modifier.size(100.dp), currentCard)
        SwipeCard(
            modifier = Modifier
                .fillMaxWidth()
                //.align()
                .padding(bottom = 32.dp),
            cardCount = cardCount,
            currentCard = currentCard,
            onSwipe = { newIndex ->
                currentCard = newIndex
            }
        )

        // Add icons indicating the current card here
        // You can use Row and Icon composable for this
    }
}


@Composable
fun Logo(modifier: Modifier = Modifier, currentCard: Int) {
    // Define an array of drawable resource IDs for each card's image
    val cardImageIds = arrayOf(
        R.drawable.tpllogo,
        R.drawable.phone,
        R.drawable.splashlogo,
        R.drawable.splashlogo,
        R.drawable.tpllogo
    )

    val logo: Painter = painterResource(id = cardImageIds[currentCard])
    Image(
        painter = logo,
        contentDescription = null,
        modifier = modifier
    )
}


@Composable
fun SwipeCard(
    modifier: Modifier = Modifier,
    cardCount: Int,
    currentCard: Int,
    onSwipe: (Int) -> Unit,
) {
    val offsetX = remember { mutableStateOf(0f) }

    Card(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, top = 500.dp, bottom = 25.dp)
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _ ,_ ->
                    offsetX.value += pan.x
                }

                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount.absoluteValue >= 0.5f) {
                        val newIndex = (currentCard + dragAmount.sign.toInt()).coerceIn(0, cardCount - 1)
                        onSwipe(newIndex)
                    }
                }
            },

        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 30.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Your card content here
            Text(
                text = stringResource(id = R.string.app_name),
                //style = MaterialTheme.typography.displayMedium,
                fontSize = 30.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = stringResource(id = R.string.app_name),
                //style = MaterialTheme.typography.labelSmall
                fontSize = 18.sp,
            )

            StartButton()
        }
    }
}


@Composable
fun StartButton() {
    val continueButtonColor = Color(android.graphics.Color.parseColor("#EC600D"))

    Button(
        onClick = {
        },
        modifier = Modifier
            .padding(16.dp)
            .size(width = 150.dp, height = 20.dp)
            .clip(RoundedCornerShape(percent = 50))
            .padding(horizontal = 32.dp),
        colors = ButtonDefaults.buttonColors(continueButtonColor)
    ) {
        Text(
            text = "Get Started",
            fontSize= 6.sp,
            color = Color.White,
        )
    }
}

@Preview
@Composable
fun OnboardPreview() {
    TPLTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            OnboardScreen()
        }
    }
}
