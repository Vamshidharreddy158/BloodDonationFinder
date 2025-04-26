package com.example.blooddonation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConverterUserStatusCheck()
        }
    }
}

@Composable
fun ConverterUserStatusCheck() {
    val context = LocalContext.current as Activity
    var showSplash by remember { mutableStateOf(true) }

    DisposableEffect(Unit) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            showSplash = false
        }
        onDispose { job.cancel() }
    }

    if (showSplash) {
        ConverterSplashScreen()

    } else {

        val currentStatus = BloodDonationData.readLS(context)

        if (currentStatus) {
            context.startActivity(Intent(context, BloodDonationHome::class.java))
            context.finish()

        } else {
            context.startActivity(Intent(context, EnterAppActivity::class.java))
            context.finish()
        }

    }

}

@Composable
fun ConverterSplashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.color3),
            ),
    ) {

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

            Image(
                painter = painterResource(id = R.drawable.blood_donation), // Replace with your actual SVG drawable
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Blood Donation App",
            color = colorResource(id = R.color.fg_color),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),

            )


        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "By\nVamshidhar Reddy",
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.fg_color),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),

            )


        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),

            )

        Spacer(modifier = Modifier.height(18.dp))


    }

}

@Preview(showBackground = true)
@Composable
fun ConverterSplashScreenPreview() {
    ConverterSplashScreen()
}