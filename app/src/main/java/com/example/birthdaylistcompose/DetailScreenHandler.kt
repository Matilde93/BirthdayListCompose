package com.example.birthdaylistcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

class DetailScreenHandler(private val navController: NavController) {
    @Composable
    fun DetailScreen(string: String){
        val viewModel = PersonViewModel()
        val personById = remember {mutableStateOf<Person?>(value = null)}
        val coroutineScope = rememberCoroutineScope()

        // Use LaunchedEffect to call the getPersonById function when the screen is initially composed
        LaunchedEffect(string) {
            coroutineScope.launch {
                // Call the getPersonById function within the coroutine scope
                val person = viewModel.getPersonById(string)

                // Update the state with the result
                personById.value = person
            }
        }
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
        val onUpdateButtonClick: () -> Unit = {
            val personData = Person(
                name = name.value.text.ifEmpty { personById.value?.name ?: "" },
                remarks = remarks.value.text.ifEmpty { personById.value?.remarks ?: "" },
                birthDayOfMonth = day.value.text.toIntOrNull() ?: personById.value?.birthDayOfMonth ?: 0,
                birthMonth = month.value.text.toIntOrNull() ?: personById.value?.birthMonth ?: 0,
                birthYear = year.value.text.toIntOrNull() ?: personById.value?.birthYear ?: 0,
                pictureUrl = "",
                id = 0,
                userId = "matilde@matilde.dk",
                age = 0
            )


            //Call API update here
            viewModel.updatePerson(personData, personById.value!!.id)

            // Optionally, you can navigate back to the list screen after posting
            navController.popBackStack()
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Update or Delete Person",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)

            )
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                placeholder = { Text(text = personById.value?.name ?: "Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
            )
            TextField(
                value = remarks.value,
                onValueChange = {remarks.value = it},
                placeholder = {
                    if(personById.value?.remarks != null){
                        Text(text = personById.value?.remarks.toString())
                    } else {
                        Text(text = "Remarks..")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
            )
            Row {
                TextField(
                    value = day.value,
                    onValueChange = {day.value = it},
                    placeholder = { Text(text = personById.value?.birthDayOfMonth.toString()) },
                    modifier = Modifier
                        .padding(start = 32.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1f)
                )
                TextField(
                    value = month.value,
                    onValueChange = {month.value = it},
                    placeholder = { Text(text = personById.value?.birthMonth.toString()) },
                    modifier = Modifier
                        .padding(start = 0.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1f)

                )
                TextField(
                    value = year.value,
                    onValueChange = {year.value = it},
                    placeholder = { Text(text = personById.value?.birthYear.toString()) },
                    modifier = Modifier
                        .padding(start = 0.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
                        .weight(1f)

                )

            }
            Row {
                Button(
                    onClick = onUpdateButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    Text(text = "Update Person")
                }
                Button(
                    onClick = {
                        viewModel.deletePerson(personById.value!!.id)

                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f)
                ) {
                    Text(text = "Delete Person")
                }
            }

        }
    }
}