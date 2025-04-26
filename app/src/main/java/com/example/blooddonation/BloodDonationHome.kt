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
import androidx.compose.material.icons.filled.AccountCircle
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BloodDonationHome : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodDonorHomeActivity()
        }
    }
}

@Composable
fun BloodDonorHomeActivity() {

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.color3))
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
                    context.startActivity(Intent(context, UserDataActivity::class.java))

                },
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Account Circle",
                tint = Color.White
            )

            Text(
                modifier = Modifier
                    .padding(12.dp),
                text = "Blood Donation App",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold

            )
        }


        BloodDonationCard1()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Column(
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, SearchDonorActivity::class.java))
                    }
                    .weight(1f)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(6.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    modifier = Modifier
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.add_participant),
                    contentDescription = "Add Participant"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    text = "Search Donors",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        context.startActivity(Intent(context, ManageDonorsActivity::class.java))
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    modifier = Modifier
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.manage_detail),
                    contentDescription = "Manage Details"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    text = "Manage Details",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        context.startActivity(Intent(context, LocateBloodBanksActivity::class.java))

                    }
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(6.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    modifier = Modifier
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.iv_donationcentre),
                    contentDescription = "Blood Bank"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    text = "Blood \nBanks",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Column(
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, DonatioProcessActivity::class.java))
                    }
                    .weight(1f)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(6.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    modifier = Modifier
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.iv_donation_process),
                    contentDescription = "Process"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    text = "Donation\nProcess",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        context.startActivity(Intent(context, EligibilityActivity::class.java))
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    modifier = Modifier
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.iv_eligibility),
                    contentDescription = "Eligibility"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    text = "Who can\ngive blood?",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        context.startActivity(Intent(context, SupportActivity::class.java))

                    }
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(6.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    modifier = Modifier
                        .size(48.dp),
                    painter = painterResource(id = R.drawable.iv_support),
                    contentDescription = "Support"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
                        )
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.color1),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    text = "Feedback &\nSupport",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

            }
        }
    }

}

@Composable
fun BloodDonationCard1() {
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .background(color = colorResource(R.color.white))
            .padding(8.dp)

    ) {

        Text(
            modifier = Modifier,
            text = "You can become a Blood Donor in",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                BloodDonationCard2()

                Text(
                    modifier = Modifier
                        .clickable {
                            context.startActivity(Intent(context, DonateBloodActivity::class.java))
                        },
                    text = "Become a Donor Now",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier
                    .size(128.dp),
                painter = painterResource(id = R.drawable.blood_donor),
                contentDescription = "Dlood donor"
            )

        }

    }

}

@Composable
fun BloodDonationCard2() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = "4",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 84.sp
            )
        )
        Spacer(modifier = Modifier.width(6.dp))

        Text(
            modifier = Modifier,
            text = "Easy \nSteps",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.Black,
                fontWeight = FontWeight.Bold,


                )
        )


    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BloodDonorHomeActivity()
}