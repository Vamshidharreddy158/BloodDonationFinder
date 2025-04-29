package teesside.s3381983.blooddonation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchDonorActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchDonors()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SearchDonors() {
    val context = LocalContext.current as Activity
    val userEmail = BloodDonationPreferences.fetchDonorEmail(context)
    var donorsList by remember { mutableStateOf(listOf<DonorData>()) }
    var loadDonors by remember { mutableStateOf(true) }

    var searchQuery by remember { mutableStateOf("") }

    val filteredDonors = donorsList.filter {
        (it.fullname.contains(searchQuery, ignoreCase = true) ||
                it.bloodGroup.contains(searchQuery, ignoreCase = true)) && it.isAvailable
    }


    LaunchedEffect(userEmail) {
        getDonors() { donors ->
            donorsList = donors
            loadDonors = false
        }
    }


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
                    (context as Activity).finish()
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )

            Text(
                modifier = Modifier
                    .padding(12.dp),
                text = "Search Donors",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold

            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Donors by Name or Blood Group") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))



            if (filteredDonors.isNotEmpty()) {
                LazyColumn {
                    items(filteredDonors.chunked(2)) { pair ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            pair.forEach { donor ->
                                if (donor.isAvailable) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        DonorCard(donor)
                                    }
                                }
                            }
                            if (pair.size < 2) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            } else {
                Text(
                    modifier = Modifier
                        .padding(12.dp),
                    text = "No Donors Found",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}


@Composable
fun DonorCard(donorItem: DonorData) {

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.drawable.iv_donor),
                contentDescription = "Donor"
            )

            Spacer(modifier = Modifier.height(6.dp))


            Text(donorItem.fullname, fontWeight = FontWeight.Bold, color = Color.Black)

            Text("Blood Group: ${donorItem.bloodGroup}", color = Color.Black)

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                modifier = Modifier
                    .clickable {
                        showDialog = true
                    }
                    .background(color = Color.Black)
                    .padding(vertical = 3.dp, horizontal = 6.dp),
                text = "See Details",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))


            if (showDialog) {
                CustomDonorDialog(
                    donor = donorItem,
                    onDismiss = { showDialog = false }
                )
            }

        }
    }
}

fun getDonors(callback: (List<DonorData>) -> Unit) {

    val databaseReference = FirebaseDatabase.getInstance().getReference("BloodDonors")

    databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val donorList = mutableListOf<DonorData>()

            for (donorSnapshot in snapshot.children) {
                for (dSnapShot in donorSnapshot.children) {
                    val donor = dSnapShot.getValue(DonorData::class.java)
                    donor?.let { donorList.add(it) }
                }
            }

            callback(donorList)
        }

        override fun onCancelled(error: DatabaseError) {
            println("Error: ${error.message}")
            callback(emptyList())
        }
    })
}

@Composable
fun CustomDonorDialog(
    donor: DonorData,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Donor Info",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Text(modifier = Modifier.weight(1f), text = "Name")
                    Text(":")
                    Text(modifier = Modifier.weight(1f), text = " ${donor.fullname}")
                }
                Row {
                    Text(modifier = Modifier.weight(1f), text = "DOB")
                    Text(":")
                    Text(modifier = Modifier.weight(1f), text = " ${donor.dob}")
                }
                Row {
                    Text(modifier = Modifier.weight(1f), text = "Blood Group")
                    Text(":")
                    Text(modifier = Modifier.weight(1f), text = " ${donor.bloodGroup}")
                }
                Row {
                    Text(modifier = Modifier.weight(1f), text = "Phone")
                    Text(":")
                    Text(modifier = Modifier.weight(1f), text = " ${donor.phoneNumber}")
                }
                Row {
                    Text(modifier = Modifier.weight(1f), text = "Email")
                    Text(":")
                    Text(modifier = Modifier.weight(1f), text = " ${donor.email}")
                }
                Row {
                    Text(modifier = Modifier.weight(1f), text = "Location")
                    Text(":")
                    Text(modifier = Modifier.weight(1f), text = " ${donor.location}")
                }
                Row {
                    Text(modifier = Modifier.weight(1f), text = "Donated Before")
                    Text(":")
                    Text(modifier = Modifier.weight(1f), text = " ${donor.donatedBefore}")
                }
                Row {
                    Text(modifier = Modifier.weight(1f), text = "Disease")
                    Text(":")
                    Text(modifier = Modifier.weight(1f), text = " ${donor.anyDiseases}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Close")
                    }
                    val context = LocalContext.current
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${donor.phoneNumber}")
                        }
                        context.startActivity(intent)
                    }) {
                        Text("Call Now")
                    }
                }
            }
        }
    }
}


