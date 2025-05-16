package com.example.catalift_tech

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import com.google.accompanist.flowlayout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SecondScreen(profession: String,company : String ) {
    val navigator = LocalNavHostController.current
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val interests = remember {
        listOf(
            "Web Development", "Android Development", "Machine Learning", "Artificial Intelligence",
            "Cybersecurity", "UI/UX Design", "Data Science", "Game Development",
            "Cloud Computing", "Blockchain", "DevOps", "Competitive Programming",
            "Robotics", "IoT", "AR/VR", "Graphic Design", "Video Editing",
            "Public Speaking", "Content Writing", "Photography", "Music", "Drawing",
            "Startup", "Finance", "Marketing", "Leadership", "Time Management",
            "Fitness", "Meditation", "Blogging", "Traveling", "Reading"
        )

    }

    var selectedInterests by remember { mutableStateOf(setOf<String>()) }
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = Color.White)
            .padding(start=16.dp,top=38.dp, bottom = 20.dp,end=16.dp),
    ) {

        // Progress Bar
        LinearProgressIndicator(
            progress = 0.8f, // Adjust as needed
            color = Color(0xFF0A0A5F),
            trackColor = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = "Your Interests",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF0A0A5F)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Select the topics and activities that excite you the most. This helps us tailor opportunities, events, and content that match your passion and goals.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))


        CustomSearchBox(
            searchText = searchText,
            onSearchChanged = { searchText = it }
        )



        Spacer(modifier = Modifier.height(16.dp))

        // search krne ke liye bina case sensitive ke
        val filtered = interests.filter {
            it.contains(searchText, ignoreCase = true)
        }

        FlowRow(
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 10.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            filtered.forEach { interest ->
                val selected = selectedInterests.contains(interest)
                OutlinedButton(
                    onClick = {
                        selectedInterests = if (selected) {
                            selectedInterests - interest
                        } else {
                            selectedInterests + interest
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (selected) Color(0xFF0A0A5F) else Color.Transparent,
                        contentColor = if (selected) Color.White else Color(0xFF0A0A5F)
                    ),
                    border = BorderStroke(1.dp, Color(0xFF0A0A5F)),
                    shape = RoundedCornerShape(30.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Text(text = interest)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Buttons
        Column{

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    val intent =Intent(context,Details::class.java)
                    intent.putExtra("profession", profession)
                    intent.putExtra("company", company)
                    intent.putExtra("interests", selectedInterests.joinToString(", "))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A0A5F))
            ) {
                Text("Continue", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = {
                    navigator.navigate("firstScreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFF0A0A5F))
            ) {
                Text("Back", color = Color(0xFF0A0A5F))
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}





@Composable
fun CustomSearchBox(searchText: String, onSearchChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color(0xFFF2F2F2), shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = searchText,
                onValueChange = onSearchChanged,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (searchText.isEmpty()) {
                        Text(
                            text = "Search",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}
