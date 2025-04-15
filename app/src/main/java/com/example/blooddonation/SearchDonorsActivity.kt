package com.example.blooddonation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class SearchDonorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DonorListScreen()
        }
    }
}


data class Donor(
    val name: String,
    val location: String,
    val bloodGroup: String,
    val age: Int,
    val contact: String
)

@Composable
fun DonorListScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val donors = listOf(
        Donor("Alice Smith", "Middlesbrough", "A+", 28, "1234567890"),
        Donor("Bob Johnson", "Redcar", "O+", 35, "2345678901"),
        Donor("Clara White", "Northallerton", "B+", 30, "3456789012"),
        Donor("David Brown", "Hartlepool", "AB-", 42, "4567890123"),
        Donor("Ella Green", "Redcar", "O-", 25, "5678901234"),
        Donor("Frank Black", "Middlesbrough", "A-", 38, "6789012345"),
        Donor("Grace Lee", "Northallerton", "B-", 29, "7890123456"),
        Donor("Henry King", "Hartlepool", "AB+", 33, "8901234567"),
        Donor("Ivy Gray", "Middlesbrough", "O+", 24, "9012345678"),
        Donor("Jack Hill", "Redcar", "A+", 41, "0123456789"),
        Donor("Kara Snow", "Northallerton", "B+", 26, "1029384756"),
        Donor("Leo Fox", "Hartlepool", "O-", 36, "1928374650"),
        Donor("Mia Wood", "Redcar", "AB+", 27, "5647382910"),
        Donor("Nate Reed", "Middlesbrough", "A-", 32, "6574839201"),
        Donor("Olivia Ray", "Hartlepool", "B-", 39, "3847562910")
    )

    val filteredDonors = donors.filter {
        val query = searchQuery.text.trim().lowercase()
        it.location.lowercase().contains(query) || it.bloodGroup.lowercase().contains(query)
    }

    Column() {

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

                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Select Contact",
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
            modifier = Modifier.padding(16.dp)
        ) {


            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by Location or Blood Group") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            LazyColumn {
                items(filteredDonors) { donor ->
                    DonorCard(donor)
                }
            }
        }
    }
}

@Composable
fun DonorCard(donor: Donor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Name: ${donor.name}", fontWeight = FontWeight.Bold)
            Text(text = "Location: ${donor.location}")
            Text(text = "Blood Group: ${donor.bloodGroup}")
            Text(text = "Age: ${donor.age}")
            Text(text = "Contact: ${donor.contact}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDonorListScreen() {
    DonorListScreen()
}
