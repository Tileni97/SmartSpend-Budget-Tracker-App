package com.example.smartspend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.firebase.AuthViewModel

@Composable
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    viewModel: AuthViewModel

) {
    var email by remember { mutableStateOf("") }
    val resetPasswordState by viewModel.resetPasswordState.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xff009177)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Reset Password",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter your email") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.resetPassword(email)
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .width(200.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Text("Reset Password")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (resetPasswordState) {
                AuthViewModel.ResetPasswordState.Loading -> {
                    CircularProgressIndicator()
                }
                is AuthViewModel.ResetPasswordState.Error -> {
                    val errorMessage = (resetPasswordState as AuthViewModel.ResetPasswordState.Error).message
                    Text(
                        text = errorMessage,
                        color = Color.Red
                    )
                }
                is AuthViewModel.ResetPasswordState.Success -> {
                    Text(
                        text = "Password reset email sent successfully",
                        color = Color.Green
                    )
                }
                else -> {}
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBackPressed,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .width(200.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                Text("Back")
            }
        }
    }
}