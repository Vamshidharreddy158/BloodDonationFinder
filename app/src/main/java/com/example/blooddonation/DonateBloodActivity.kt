package com.example.blooddonation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DonateBloodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DonateBlood()
        }
    }
}

@Composable
fun DonateBlood()
{
    var fullName by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.color3)),
//        horizontalAlignment = Alignment.CenterHorizontally
    ){



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.color1)
                )
                .padding(horizontal = 12.dp),

            verticalAlignment = Alignment.CenterVertically,


            ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Select Contact",
                tint = Color.White
            )

            Text(
                modifier = Modifier
                    .padding(12.dp),
                text = "Donate Blood",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold

            )
        }

        Spacer(modifier = Modifier.height(24.dp))

//        Text(
//            modifier = Modifier
//                .padding(horizontal = 12.dp),
//            text = "Full",
//            fontSize = 18.sp,
//            style = MaterialTheme.typography.titleMedium.copy(
//                color = colorResource(id = R.color.black),
//            )
//        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.white),
                focusedContainerColor = colorResource(R.color.white),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            ),
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Enter Full Name") },
            textStyle = TextStyle(color = colorResource(id = R.color.black)),

            )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.white),
                focusedContainerColor = colorResource(R.color.white),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            ),
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Enter Date of Birth") },
            textStyle = TextStyle(color = colorResource(id = R.color.black)),

            )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.white),
                focusedContainerColor = colorResource(R.color.white),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            ),
            value = bloodGroup,
            onValueChange = { bloodGroup = it },
            label = { Text("Enter Blood Group") },
            textStyle = TextStyle(color = colorResource(id = R.color.black)),

            )


        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.white),
                focusedContainerColor = colorResource(R.color.white),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            ),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Enter Phone Number") },
            textStyle = TextStyle(color = colorResource(id = R.color.black)),

            )


        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.white),
                focusedContainerColor = colorResource(R.color.white),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            ),
            value = userEmail,
            onValueChange = { userEmail = it },
            label = { Text("Enter Email") },
            textStyle = TextStyle(color = colorResource(id = R.color.black)),

            )


        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.white),
                focusedContainerColor = colorResource(R.color.white),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            ),
            value = location,
            onValueChange = { location = it },
            label = { Text("Enter Loaction") },
            textStyle = TextStyle(color = colorResource(id = R.color.black)),

            )

        Spacer(modifier = Modifier.height(6.dp))

        BloodDonationQuestion1()

        BloodDonationQuestion2()

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier
                .clickable {
                }
                .width(200.dp)
                .padding(horizontal = 12.dp)
                .background(
                    color = colorResource(id = R.color.color1),
                    shape = RoundedCornerShape(
                        10.dp
                    )
                )
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.color1),
                    shape = RoundedCornerShape(
                        10.dp
                    )
                )
                .padding(vertical = 12.dp, horizontal = 12.dp)
                .align(Alignment.CenterHorizontally),
            text = "Submit",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = colorResource(id = R.color.white),
            )
        )


    }
}


@Composable
fun BloodDonationQuestion1() {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Did you donate the blood before?",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == "Yes",
                    onClick = { selectedOption = "Yes" }
                )
                Text("Yes", modifier = Modifier.clickable { selectedOption = "Yes" })
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == "No",
                    onClick = { selectedOption = "No" }
                )
                Text("No", modifier = Modifier.clickable { selectedOption = "No" })
            }
        }
    }
}


@Composable
fun BloodDonationQuestion2() {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Are you suffering from any disease? ",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == "Yes",
                    onClick = { selectedOption = "Yes" }
                )
                Text("Yes", modifier = Modifier.clickable { selectedOption = "Yes" })
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == "No",
                    onClick = { selectedOption = "No" }
                )
                Text("No", modifier = Modifier.clickable { selectedOption = "No" })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DonateBloodPreview() {
    DonateBlood()
}