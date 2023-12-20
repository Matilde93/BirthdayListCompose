package com.example.birthdaylistcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.ui.layout.Align
import com.google.firebase.auth.FirebaseAuth

class AddScreenHandler(private val navController: NavController){
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AddPerson(){
        val viewModel = PersonViewModel()
        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        val name = remember {
            mutableStateOf(TextFieldValue())
        }
        val remarks = remember {
            mutableStateOf(TextFieldValue())
        }
        val day = remember {
            mutableStateOf(TextFieldValue())
        }
        val month = remember {
            mutableStateOf(TextFieldValue())
        }
        val year = remember {
            mutableStateOf(TextFieldValue())
        }
        val onAddButtonClick: () -> Unit = {
            val personData = Person(
                name = name.value.text,
                remarks = remarks.value.text,
                birthDayOfMonth = day.value.text.toIntOrNull() ?: 1,
                birthMonth = month.value.text.toIntOrNull() ?: 1,
                birthYear = year.value.text.toIntOrNull() ?: 1999,
                pictureUrl = null,
                id = 0,
                userId = auth.currentUser?.email.toString(),
                age = 0
            )

            //Call API post here
            viewModel.addPerson(personData)


            // Optionally, you can navigate back to the list screen after posting
            navController.popBackStack()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Add Person",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)

            )
            TextField(
                value = name.value,
                onValueChange = {name.value = it},
                placeholder = { Text(text = "Name..") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
                )
            TextField(
                value = remarks.value,
                onValueChange = {remarks.value = it},
                placeholder = { Text(text = "Remarks..") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
            )
            Row {
                TextField(
                    value = day.value,
                    onValueChange = {day.value = it},
                    placeholder = { Text(text = "dd") },
                    modifier = Modifier
                        .padding(start = 32.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1f)
                )
                TextField(
                    value = month.value,
                    onValueChange = {month.value = it},
                    placeholder = { Text(text = "mm") },
                    modifier = Modifier
                        .padding(start = 0.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1f)

                )
                TextField(
                    value = year.value,
                    onValueChange = {year.value = it},
                    placeholder = { Text(text = "yyyy") },
                    modifier = Modifier
                        .padding(start = 0.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1f)

                )

            }
            Button(
                onClick = onAddButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Add Person")
            }
            }
        }
    }


