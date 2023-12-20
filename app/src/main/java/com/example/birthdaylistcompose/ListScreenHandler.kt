package com.example.birthdaylistcompose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.safe.args.generator.models.Destination
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ListScreenHandler(private val navController: NavController){
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun ListScreen(){
        val viewModel = PersonViewModel()
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        viewModel.getPersonList()

        if (viewModel.errorMessage.isEmpty()) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Birthday List") },
                        actions = {
                            IconButton(onClick = {
                                Firebase.auth.signOut()
                                navController.navigate(Screen.LoginScreen.route)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = "Log out"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                }
            ) {
                Column(modifier = Modifier.padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        items(viewModel.personList.filter { it.userId == auth.currentUser?.email.toString() }) { person ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(MaterialTheme.colorScheme.primary)
                                            .padding(0.dp, 10.dp, 16.dp, 10.dp)
                                            .clickable(onClick = {
                                                navController.navigate(Screen.DetailScreen.withArgs(person.id.toString()))
                                            })
                                    ) {
                                        Column {
                                            Row (
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                            ) {
                                                Text(
                                                    text = person.name,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 24.sp,
                                                    color = MaterialTheme.colorScheme.primaryContainer
                                                )
                                            }
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(start = 15.dp)
                                            ) {
                                                Text(
                                                    text = "Is turning " + (person.age + 1).toString() + " years old on " + person.birthDayOfMonth + "/" + person.birthMonth,
                                                    fontSize = 15.sp,
                                                    fontStyle = FontStyle.Italic,
                                                    color = MaterialTheme.colorScheme.primaryContainer
                                                )
                                            }

                                        }

                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                }
                            }
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(Screen.AddScreen.route)
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                }
            }

        } else {
            Text(viewModel.errorMessage)
        }
    }
}
