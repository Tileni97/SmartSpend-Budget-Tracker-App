package com.example.smartspend.Screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartspend.firebase.AuthViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = viewModel(),
    navigateToAccountSetUp: (FirebaseUser) -> Unit // Callback function to navigate to AccountSetUp screen
) {
    val passwordVisibility = remember { mutableStateOf(false) } // State to control password visibility
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current

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
                text = "Login To your account",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                placeholder = { Text("Password") },
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    val image = if (passwordVisibility.value)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(
                        onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }
                    ) {
                        Icon(imageVector = image, contentDescription = "Toggle Password Visibility")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.signIn(username, password)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (loginState) {
                AuthViewModel.LoginState.Loading -> {
                    CircularProgressIndicator()
                }

                is AuthViewModel.LoginState.Error -> {
                    val errorMessage = (loginState as AuthViewModel.LoginState.Error).message
                    Text(
                        text = errorMessage,
                        color = Color.Red
                    )
                    // Show a notification toast for the error
                    LaunchedEffect(errorMessage) {
                        showToast(context, errorMessage)
                    }
                }

                is AuthViewModel.LoginState.Success -> {
                    val user = (loginState as AuthViewModel.LoginState.Success).user
                    // Print the signed-in user to the terminal for monitoring
                    println("Signed-in user: $user")
                    // Navigate to the AccountSetUp screen
                    navigateToAccountSetUp(user)
                    // Show a toast for successful login
                    LaunchedEffect(Unit) {
                        showToast(context, "Login Successful")
                    }
                }

                else -> {}

            }


            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text("Forgot Password? ", color = Color.White)
                Text(
                    "Reset Password",
                    color = Color.White,
                    modifier = Modifier.clickable(onClick = { /* Handle reset password click */ })
                )
            }
        }
    }
}

/**
 * Shows a notification toast with the given message.
 *
 * @param context The current context.
 * @param message The message to display in the toast.
 */
private suspend fun showToast(context: Context, message: String) {
    // Implement your toast notification logic here
    // For example, you could use the Android Toast API:
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    // Delay for demonstration purposes
    delay(1000)
    println("Toast: $message")
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navigateToAccountSetUp = {})
}