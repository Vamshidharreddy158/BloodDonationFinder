package teesside.s3381983.blooddonation

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

class UpdateDonorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpdateDonateBlood()
        }
    }
}

object SelectedDonor {
    lateinit var donorData: DonorData
}

@Composable
fun UpdateDonateBlood() {
    var fullName by remember { mutableStateOf(SelectedDonor.donorData.fullname) }
    var dateOfBirth by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf(SelectedDonor.donorData.phoneNumber) }
    var userEmail by remember { mutableStateOf(SelectedDonor.donorData.email) }
    var location by remember { mutableStateOf(SelectedDonor.donorData.location) }

    var selectedGroup by remember { mutableStateOf(SelectedDonor.donorData.bloodGroup) }

    var donatedBefore by remember { mutableStateOf<String?>(SelectedDonor.donorData.donatedBefore) }
    var haveDiseases by remember { mutableStateOf<String?>(SelectedDonor.donorData.anyDiseases) }

    var availabilityText by remember { mutableStateOf<String?>(null) }

    var availability by remember { mutableStateOf<Boolean>(false) }

    var isAvailabilitySelected by remember { mutableStateOf<Boolean>(false) }


    val context = LocalContext.current as Activity

    val calendar = Calendar.getInstance()

    var selYear by remember { mutableIntStateOf(0) }
    var selMonth by remember { mutableIntStateOf(0) }
    var selDOM by remember { mutableIntStateOf(0) }
    var selHOD by remember { mutableIntStateOf(0) }
    var selMinute by remember { mutableIntStateOf(0) }

    var dateState by remember { mutableStateOf(SelectedDonor.donorData.dob) }


    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selYear = year
            selMonth = month
            selDOM = dayOfMonth


            Log.e("Test", "Y - $year , M - ${month + 1} , DOM - $dayOfMonth")

            dateState = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

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
                text = "Update Donor",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold

            )
        }

        Spacer(modifier = Modifier.height(24.dp))


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


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp)
                .height(50.dp)
                .clickable {
                    // Handle the click event, e.g., show a date picker
                }
                .background(Color.LightGray, MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = dateState.ifEmpty { "Date Of Birth" },
                color = if (dateState.isEmpty()) Color.Gray else Color.Black
            )
            Icon(
                imageVector = Icons.Default.DateRange, // Replace with your desired icon
                contentDescription = "Calendar Icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(24.dp)
                    .clickable {
                        datePickerDialog.show()
                    },
                tint = Color.DarkGray
            )
        }

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
            label = { Text("Enter Location") },
            textStyle = TextStyle(color = colorResource(id = R.color.black)),

            )

        Spacer(modifier = Modifier.height(6.dp))

//        BloodDonationQuestion1()

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = "Selected Blood Group",
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        BloodGroupSelector(
            selectedGroup = selectedGroup,
            onSelectionChanged = { selectedGroup = it }
        )

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
                        selected = donatedBefore == "Yes",
                        onClick = { donatedBefore = "Yes" }
                    )
                    Text("Yes", modifier = Modifier.clickable { donatedBefore = "Yes" })
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = donatedBefore == "No",
                        onClick = { donatedBefore = "No" }
                    )
                    Text("No", modifier = Modifier.clickable { donatedBefore = "No" })
                }
            }
        }

//        BloodDonationQuestion2()

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
                        selected = haveDiseases == "Yes",
                        onClick = { haveDiseases = "Yes" }
                    )
                    Text("Yes", modifier = Modifier.clickable { haveDiseases = "Yes" })
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = haveDiseases == "No",
                        onClick = { haveDiseases = "No" }
                    )
                    Text("No", modifier = Modifier.clickable { haveDiseases = "No" })
                }
            }

            Text(
                text = "Availability to donate? ",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = availabilityText=="A",
                        onClick = {
                            availabilityText="A"
                            availability = true
                            isAvailabilitySelected = true

                        }
                    )
                    Text("Yes", modifier = Modifier.clickable {
                        availabilityText="A"
                        availability = true
                        isAvailabilitySelected = true

                    })
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = availabilityText=="B",
                        onClick = {
                            availabilityText="B"
                            availability = false
                            isAvailabilitySelected = true

                        }
                    )
                    Text("Not Available", modifier = Modifier.clickable {
                        availabilityText=="B"
                        availability = false
                        isAvailabilitySelected = true

                    })
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = availabilityText=="C",
                        onClick = {
                            availabilityText="C"
                            availability = false
                            isAvailabilitySelected = true

                        }
                    )
                    Text(
                        "No, Already Donated",
                        modifier = Modifier.clickable {
                            availabilityText=="C"
                            availability = false
                            isAvailabilitySelected = true
                        })
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier
                .clickable {
                    if (fullName.isEmpty() && isAvailabilitySelected) {
                        Toast
                            .makeText(context, "Fields Missing", Toast.LENGTH_SHORT)
                            .show()
                    } else {

                        val updateDonor = mapOf(
                            "donationId" to SelectedDonor.donorData.donationId,
                            "fullname" to fullName,
                            "dob" to dateState,
                            "bloodGroup" to selectedGroup,
                            "phoneNumber" to phoneNumber,
                            "email" to userEmail,
                            "location" to location,
                            "donatedBefore" to donatedBefore!!,
                            "anyDiseases" to haveDiseases!!,
                            "available" to availability
                        )

                        updateDonor(
                            SelectedDonor.donorData.donationId,
                            updateDonor,
                            context
                        )

                    }
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
            text = "Save Donor",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium.copy(
                color = colorResource(id = R.color.white),
            )
        )

        Spacer(modifier = Modifier.height(24.dp))


    }
}

fun updateDonor(donorId: String, updatedData: Map<String, Any>, context: Context) {

    try {
        val emailKey = BloodDonationPreferences.fetchDonorEmail(context)
            .replace(".", ",")
        val path = "BloodDonors/$emailKey/$donorId"
        FirebaseDatabase.getInstance().getReference(path).updateChildren(updatedData)
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Donor Updated Successfully",
                    Toast.LENGTH_SHORT
                ).show()

                (context as Activity).finish()
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Failed to update",
                    Toast.LENGTH_SHORT
                ).show()
            }
    } catch (e: Exception) {
        Log.e("Test", "Error Message : ${e.message}")
    }
}