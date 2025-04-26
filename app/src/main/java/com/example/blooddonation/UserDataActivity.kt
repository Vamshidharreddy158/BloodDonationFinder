package com.example.blooddonation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { 
            UserProfile()
        }
    }
}


@Composable
fun UserProfile() {
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

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
                modifier = Modifier.clickable {
                    (context as Activity).finish()
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )

            Text(
                modifier = Modifier
                    .padding(12.dp),
                text = "Donor Profile",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold

            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(
                        10.dp
                    )
                )
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(
                        10.dp
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))


            Image(
                painter = painterResource(id = R.drawable.iv_donor),
                contentDescription = "Profile",
                modifier = Modifier.size(96.dp)

            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "User Name")

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = BloodDonationData.readUserName(context),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "User Email")

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = BloodDonationData.readMail(context),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            BloodDonationData.writeLS(context, false)

                            context.startActivity(Intent(context, EnterAppActivity::class.java))
                            (context as Activity).finish()
                        }
                        .padding(horizontal = 12.dp)
                        .background(
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.black),
                            shape = RoundedCornerShape(
                                10.dp
                            )
                        )
                        .padding(vertical = 4.dp, horizontal = 12.dp),
                    text = "Logout",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.white),
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

            }


        }
    }
}

