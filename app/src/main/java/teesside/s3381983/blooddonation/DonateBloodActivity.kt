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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DonateBloodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DonateBlood()
        }
    }
}

@Composable
fun DonateBlood() {
    var fullName by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    var selectedGroup by remember { mutableStateOf("A+") }

    var donatedBefore by remember { mutableStateOf<String?>(null) }
    var haveDiseases by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current as Activity

    val calendar = Calendar.getInstance()

    var selYear by remember { mutableIntStateOf(0) }
    var selMonth by remember { mutableIntStateOf(0) }
    var selDOM by remember { mutableIntStateOf(0) }
    var selHOD by remember { mutableIntStateOf(0) }
    var selMinute by remember { mutableIntStateOf(0) }

    var dateState by remember { mutableStateOf("") }


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
                text = "Donate Blood",
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
            label = { Text("Enter Area") },
            textStyle = TextStyle(color = colorResource(id = R.color.black)),

            )

        Spacer(modifier = Modifier.height(6.dp))


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
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier
                .clickable {
                    if (fullName.isEmpty()) {
                        Toast
                            .makeText(context, "Fields Missing", Toast.LENGTH_SHORT)
                            .show()
                    } else {

                        val donorData = DonorData(
                            fullname = fullName,
                            dob = dateState,
                            bloodGroup = selectedGroup,
                            phoneNumber = phoneNumber,
                            email = userEmail,
                            location = location,
                            donatedBefore = donatedBefore!!,
                            anyDiseases = haveDiseases!!,
                            isAvailable = true
                        )

                        registerDonor(donorData, context)
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

@Composable
fun BloodGroupSelector(
    selectedGroup: String,
    onSelectionChanged: (String) -> Unit
) {
    val bloodGroups = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(bloodGroups) { group ->
            FilterChip(
                selected = selectedGroup == group,
                onClick = { onSelectionChanged(group) },
                label = { Text(group) },
                modifier = Modifier
                    .height(40.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DonateBloodPreview() {
    DonateBlood()
}

private fun registerDonor(donorData: DonorData, activityContext: Context) {

    val userEmail = BloodDonationPreferences.fetchDonorEmail(activityContext)
    val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
    val orderId = dateFormat.format(Date())
    donorData.donationId = orderId

    FirebaseDatabase.getInstance().getReference("BloodDonors").child(userEmail.replace(".", ","))
        .child(orderId).setValue(donorData)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(activityContext, "Donor Added Successfully", Toast.LENGTH_SHORT)
                    .show()
                (activityContext as Activity).finish()
            } else {
                Toast.makeText(
                    activityContext,
                    "Product Addition Failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener { exception ->
            Toast.makeText(
                activityContext,
                "Product Addition Failed: ${exception.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
}

data class DonorData(
    var donationId: String = "",
    var fullname: String = "",
    var dob: String = "",
    var bloodGroup: String = "",
    var phoneNumber: String = "",
    var email: String = "",
    var location: String = "",
    var donatedBefore: String = "",
    var anyDiseases: String = "",
    var isAvailable: Boolean = false
)