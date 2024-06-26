package com.example.smartspend.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen() {
    val passwordVisibility = remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordValid by remember { mutableStateOf(false) }
    var doPasswordsMatch by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff009177)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "S",
                    color = Color.White,
                    fontSize = 44.9.sp,
                )
                Text(
                    text = "S",
                    color = Color.White,
                    fontSize = 44.9.sp,
                    modifier = Modifier
                        .scale(1f, -1f) // Inverted vertically
                        .rotate(degrees = 180f) // Rotated 180 degrees to correct orientation
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Create an account",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    singleLine = true,
                    modifier = Modifier.weight(0.5f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    singleLine = true,
                    modifier = Modifier.weight(0.5f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    isPasswordValid = it.length >= 8
                },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                            passwordVisibility.value = !passwordVisibility.value
                        }
                    ) {
                        Icon(
                            imageVector = image,description

                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (!isPasswordValid) {
                Text(
                    text = "At least 8 characters",
                    color = Color.Red
                )
            } else {
                Text(
                    text = "At least 8 characters",
                    color = Color.Green
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    doPasswordsMatch = it == password
                },
                label = { Text("Confirm Password") },
                singleLine = true,
                visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    val image = if (confirmPasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description = if (confirmPasswordVisible) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(
                        onClick = {
                            confirmPasswordVisible = !confirmPasswordVisible
                            confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                        }
                    ) {
                        Icon(
                            imageVector = image,description

                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (!doPasswordsMatch) {
                Text(
                    text = "Passwords do not match",
                    color = Color.Red
                )
            } else {
                Text(
                    text = "Passwords match",
                    color = Color.Green
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.width(200.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = MaterialTheme.colorScheme.primary),

                        onClick = { /* Handle account creation */ }
            ) {
                Text("Create Account")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text("Already a member? ", color = Color.White)
                Text(
                    "Sign in",
                    color = Color.White,
                    modifier = Modifier.clickable(onClick = { /* Handle sign in click */ })
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
