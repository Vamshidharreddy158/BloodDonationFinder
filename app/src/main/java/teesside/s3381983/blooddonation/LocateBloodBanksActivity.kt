package teesside.s3381983.blooddonation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class LocateBloodBanksActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodBankMapScreen()
        }
    }
}

@Composable
fun BloodBankMapScreen(context: Context = LocalContext.current) {
    val bloodBanks = listOf(
        BloodBank(
            "NHS Blood Donor Centre",
            "26 Margaret St, London W1W 8NB",
            51.5175,
            -0.1406,
            "02071666908"
        ),
        BloodBank(
            "West End Donor Centre",
            "10 New Cavendish St, London W1G 8UL",
            51.5202,
            -0.1411,
            "02071666908"
        ),
        BloodBank(
            "Birmingham Donor Centre",
            "65 New St, Birmingham B2 4DU",
            52.4786,
            -1.9005,
            "01212788222"
        ),
        BloodBank(
            "Leeds Donor Centre",
            "Bishopgate St, Leeds LS1 5RR",
            53.7945,
            -1.5479,
            "01133623650"
        ),
        BloodBank(
            "Bristol Donor Centre",
            "Southmead Rd, Bristol BS10 5NB",
            51.5011,
            -2.5845,
            "01179882040"
        ),
        BloodBank(
            "Manchester Donor Centre",
            "Plymouth Grove, Manchester M13 9LL",
            53.4631,
            -2.2299,
            "01612514200"
        ),
        BloodBank(
            "Cambridge Donor Centre",
            "Long Rd, Cambridge CB2 8PT",
            52.1843,
            0.1405,
            "01223588000"
        ),
        BloodBank(
            "Oxford Donor Centre",
            "Headington, Oxford OX3 9DU",
            51.7548,
            -1.2175,
            "01865447900"
        ),
        BloodBank(
            "Glasgow Donor Centre",
            "Nelson Mandela Pl, Glasgow G2 1BT",
            55.8609,
            -4.2545,
            "03459090999"
        ),
        BloodBank(
            "Liverpool Donor Centre",
            "Brownlow Hill, Liverpool L3 5UE",
            53.4074,
            -2.9691,
            "03001232323"
        ),
        BloodBank(
            "Sheffield Donor Centre",
            "Glossop Rd, Sheffield S10 2JF",
            53.3782,
            -1.4891,
            "01143584700"
        ),
        BloodBank(
            "Newcastle Donor Centre",
            "Queen Victoria Rd, Newcastle NE1 4LP",
            54.9783,
            -1.6174,
            "01912194400"
        ),
        BloodBank(
            "Nottingham Donor Centre",
            "Hucknall Rd, Nottingham NG5 1PB",
            52.9831,
            -1.1566,
            "01159589588"
        ),
        BloodBank(
            "Cardiff Donor Centre",
            "Glossop Rd, Cardiff CF24 0SZ",
            51.4888,
            -3.1771,
            "02920492233"
        ),
        BloodBank(
            "Brighton Donor Centre",
            "Eastern Rd, Brighton BN2 5BE",
            50.8284,
            -0.1192,
            "03001232323"
        ),
    )


    var selectedBank by remember { mutableStateOf<BloodBank?>(null) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(53.5, -1.5), 5.8f)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.color3))
            .verticalScroll(rememberScrollState()),
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
                text = "Donations Centres",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold

            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                bloodBanks.forEach { bank ->
                    Marker(
                        state = MarkerState(position = LatLng(bank.lat, bank.lng)),
                        title = bank.name,
                        snippet = bank.address,
                        onClick = {
                            selectedBank = bank
                            true
                        }
                    )
                }
            }

            selectedBank?.let { bank ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(bank.name, fontWeight = FontWeight.Bold)
                        Text(bank.address)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${bank.phone}")
                            }
                            context.startActivity(intent)
                        }) {
                            Icon(Icons.Default.Phone, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Call Now")
                        }
                    }
                }
            }
        }
    }
}

data class BloodBank(
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val phone: String
)
