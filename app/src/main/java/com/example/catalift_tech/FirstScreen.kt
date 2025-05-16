package com.example.catalift_tech

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen() {
    val navigator = LocalNavHostController.current
    var selectedProfession by remember { mutableStateOf("Designer") }
    var selectedCompany by remember { mutableStateOf("Apple") }
    val professions = listOf("Software Engineer", "Data Scientist", "Designer", "Product Manager")
    val companies = listOf("Apple", "Google", "Amazon", "Microsoft")



    Box(modifier = Modifier.fillMaxSize().background(color=Color.White).padding(top=22.dp)) {
        Column( modifier= Modifier.padding(16.dp)){
            LinearProgressIndicator(
                progress = 0.3f, // Adjust as needed
                color = Color(0xFF0A0A5F),
                trackColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Image(
                painter = painterResource(id = R.drawable.dreamjobs),
                contentDescription = "Dream Jobs",
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)//es space from the top of the image
            )
            Text(
                text = "Your Dream Profession",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                color = Color(0xFF0A0A5F),
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp
            )

            Text(
                text = "Choose a profession you aspire to pursue in the future. This helps us understand your career vision and align recommendations and resources accordingly.",
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color.Gray,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            )

            Row(){
                Text("I want to be ...."
                    , modifier = Modifier.padding(top=20.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(){
                    DropdownSelector(
                        label = "Profession",
                        options = professions,
                        selectedOption = selectedProfession,
                        onOptionSelected = { selectedProfession = it },
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    DropdownSelector(
                        label = "Company/Industry",
                        options = companies,
                        selectedOption = selectedCompany,
                        onOptionSelected = { selectedCompany = it }
                    )

                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            val context = LocalContext.current

            //Buttons
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = {

                        navigator.navigate("secondScreen/$selectedProfession/$selectedCompany")

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(0xFF0D0D5C))
                ) {
                    Text("Continue", color = Color.White)
                }

                OutlinedButton(
                    onClick = { /* Handle Back */ },
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, Color(0xFF0D0D5C))
                ) {
                    Text("Back", color = Color(0xFF0D0D5C))
                }
            }

        }
    }
}


@Composable
fun DropdownSelector(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(top=0.dp)
    ) {
        Text(label, color = Color.Black, fontSize = 12.sp)
        Box(
            modifier = Modifier
                .background(Color(0xFFF2F2F2), RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .clickable { expanded = true }
                .padding(horizontal=5.dp,vertical=2.dp)
                .width(80.dp)
                .height(30.dp)
        ) {
            Text(selectedOption, color = Color.Black, fontSize = 14.sp)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(color = Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {  Text(
                        option,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    ) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

