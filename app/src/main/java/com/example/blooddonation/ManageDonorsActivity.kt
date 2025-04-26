package com.example.blooddonation

import android.app.Activity
import android.content.Intent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ManageDonorsActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDonors()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDonors() {
    val context = LocalContext.current as Activity
    val userEmail = BloodDonationData.readMail(context)
    var donorsList by remember { mutableStateOf(listOf<DonorData>()) }
    var loadDonors by remember { mutableStateOf(true) }

    var searchQuery by remember { mutableStateOf("") }

    val filteredDonors = donorsList.filter {
        it.fullname.contains(searchQuery, ignoreCase = true) ||
                it.bloodGroup.contains(searchQuery, ignoreCase = true)
    }


    LaunchedEffect(userEmail) {
        getMyDonors(userEmail) { donors ->
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
                text = "Manage Donors",
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
                            pair.forEach { product ->
                                Box(modifier = Modifier.weight(1f)) {
                                    MyDonorCard(product)
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
fun MyDonorCard(donorItem: DonorData) {

    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
                        SelectedDonor.donorData = donorItem
                        context.startActivity(Intent(context, UpdateDonorActivity::class.java))

                    }
                    .background(color = Color.Black)
                    .padding(vertical = 3.dp, horizontal = 6.dp),
                text = "Update",
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

fun getMyDonors(accountMail: String, callback: (List<DonorData>) -> Unit) {

    val emailKey = accountMail.replace(".", ",")
    val databaseReference = FirebaseDatabase.getInstance().getReference("BloodDonors/$emailKey")

    databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val donorsList = mutableListOf<DonorData>()

            for (donorSnap in snapshot.children) {
                val book = donorSnap.getValue(DonorData::class.java)
                book?.let { donorsList.add(it) }
            }

            callback(donorsList)
        }

        override fun onCancelled(error: DatabaseError) {
            println("Error: ${error.message}")
            callback(emptyList())
        }
    })
}

