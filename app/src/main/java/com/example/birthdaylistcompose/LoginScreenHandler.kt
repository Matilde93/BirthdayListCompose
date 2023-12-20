package com.example.birthdaylistcompose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.ui.core.TextField
import androidx.ui.layout.Column
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class LoginScreenHandler(private val navController: NavController) {
    @Composable
    fun LoginScreen(){
        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        val email = remember {
            mutableStateOf(TextFieldValue())
        }
        val password = remember {
            mutableStateOf(TextFieldValue())
        }
        var message by remember { mutableStateOf("") }

        if(auth.currentUser != null){
            navController.navigate(Screen.ListScreen.route)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Login or Register",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp, bottom = 16.dp)
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "Email..") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp, bottom = 8.dp)
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text(text = "Password..") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp, top = 8.dp)
            )
            Text(
                text = message,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(email.value.text, password.value.text)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("APPLE", "createUserWithEmail:success")
                                val user = auth.currentUser
                                navController.navigate(Screen.ListScreen.route)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("APPLE", "createUserWithEmail:failure", task.exception)
                                message = "Authentication failed: " + task.exception?.message
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp)
            ){
                Text(text = "Login")
            }
            Button(
                onClick = {
                    auth.createUserWithEmailAndPassword(email.value.text, password.value.text)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("APPLE", "createUserWithEmail:success")
                                val user = auth.currentUser
                                navController.navigate(Screen.ListScreen.route)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("APPLE", "createUserWithEmail:failure", task.exception)
                                message = "Authentication failed: " + task.exception?.message
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 32.dp)
            ){
                Text(text = "Register")
            }
        }
    }
}

