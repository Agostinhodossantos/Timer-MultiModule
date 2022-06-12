package com.hubstaff.challenge.screen.login

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.TextField as TextField1


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToMain: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            Text("Loading", fontSize = 32.sp)
        } else {

            Text("Login ", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(20.dp))
            TextField1(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextField1(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Password") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(modifier = Modifier
                .width(280.dp)
                .height(50.dp),
                onClick = {
                    coroutineScope.launch {
                        loginViewModel.signIn(email, password)
                        navigateToMain()
                    }
                }){
                Text(text = "Register")
            }
        }

    }

}