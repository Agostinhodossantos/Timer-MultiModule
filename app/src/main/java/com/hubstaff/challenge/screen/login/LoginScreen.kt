package com.hubstaff.challenge.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.material.TextField as TextField1


@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val vm = UserState.current
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (vm.isLoading) {
        } else {
            Text("Login ", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))
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
            Button(modifier = Modifier.width(280.dp).height(50.dp),
                onClick = {
                coroutineScope.launch {
                    vm.signIn(email, password)
                }
            }){
                Text(text = "Register")
            }
        }
    }
}