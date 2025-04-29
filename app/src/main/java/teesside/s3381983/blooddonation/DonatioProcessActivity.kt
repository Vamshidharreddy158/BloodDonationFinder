package teesside.s3381983.blooddonation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DonatioProcessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DonationProcessScreen()
        }
    }
}

@Composable
fun DonationProcessScreen() {
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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
                text = "The donation process",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold

            )
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)) {


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Giving blood is simple and it saves lives. When you give blood, it is collected so it can be used to treat someone else."
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "For most people, giving blood is easy and follows the simple steps listed below."
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Before you give blood",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            val beforeSteps = listOf(
                "Check you are able to give blood.",
                "Sign up online, or call 0300 123 23 23.",
                "Log in to your online account and find an appointment – a number of appointments can be booked in advance. If you are unable to book now, then please try for a later date.",
                "Follow the preparing to give blood recommendations.",
                "If you are unable to keep your donation appointment, please try and give us at least 3 days' notice. You can easily cancel or reschedule your appointment when you log in to your online account."
            )

            beforeSteps.forEach {
                Text(text = "• $it", modifier = Modifier.padding(bottom = 4.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "When you give blood",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "This is what will happen when you give blood. The whole process takes about an hour."
            )

            Spacer(modifier = Modifier.height(16.dp))

            DonationStep(
                title = "1. Welcome and preparation",
                content = listOf(
                    "Bring your completed donation safety check form if you received one by post.",
                    "Read the donor consent booklet — it’s important and updated often.",
                    "You’ll be given 500ml of fluid to drink before donating. Drink it over 5 minutes to help your well-being."
                )
            )

            DonationStep(
                title = "2. Health screening",
                content = listOf(
                    "We check your identity and review your donor health form.",
                    "A nurse may follow up if needed.",
                    "A finger-prick test checks your iron levels (haemoglobin).",
                    "If you can't donate, we’ll explain why and may ask you to book again.",
                    "If you can donate, wait in the waiting area until you're called."
                )
            )

            DonationStep(
                title = "3. Your blood donation",
                content = listOf(
                    "Confirm your name, address, and date of birth on the donation chair.",
                    "We examine your arm, apply a cuff to maintain light pressure (not blood pressure).",
                    "Find a suitable vein and clean it with an antiseptic sponge.",
                    "If we can’t draw blood the first time, we won’t try again for safety reasons.",
                    "A needle is used to collect 470ml of blood into a bag with your donor ID.",
                    "Tell staff if you feel discomfort or pain.",
                    "Blood is weighed and collection stops automatically (takes 5–10 minutes).",
                    "The needle is removed and a sterile dressing is applied."
                )
            )
        }
    }
}

@Composable
fun DonationStep(title: String, content: List<String>) {
    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Medium
    )

    Spacer(modifier = Modifier.height(4.dp))

    content.forEach {
        Text(text = "• $it", modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
    }
}
