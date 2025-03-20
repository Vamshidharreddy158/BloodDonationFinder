package com.example.blooddonation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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

class EnterAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnterAppScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterAppScreen() {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.bg_color),
            ),
    ) {

        Spacer(modifier = Modifier.height(54.dp))
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
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),

            )


        Spacer(modifier = Modifier.height(54.dp))


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = userEmail,
            onValueChange = { userEmail = it },
            placeholder = { Text("Enter Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = Color.White
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.Yellow,
                focusedTextColor = Color.White,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            value = userPassword,
            onValueChange = { userPassword = it },
            placeholder = { Text("Enter Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Email Icon",
                    tint = Color.White
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.Yellow,
                focusedTextColor = Color.White,
                )
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .width(200.dp)
                .padding(horizontal = 12.dp)
                .background(
                    color = colorResource(id = R.color.fg_color),
                    shape = RoundedCornerShape(
                        10.dp
                    )
                )
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.fg_color),
                    shape = RoundedCornerShape(
                        10.dp
                    )
                )
                .padding(vertical = 12.dp, horizontal = 12.dp)
                .align(Alignment.CenterHorizontally),
            text = "SignIn",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = colorResource(id = R.color.bg_color),
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    context.startActivity(Intent(context, JoinAppActivity::class.java))
                    context.finish()
                },
            text = "Or SignUp For Account",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = colorResource(id = R.color.fg_color),
            )
        )

        Spacer(modifier = Modifier.weight(1f))


    }
}

@Preview(showBackground = true)
@Composable
fun EnterAppScreenPreview() {
    EnterAppScreen()
}