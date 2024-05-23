package com.example.smartspend.admin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.smartspend.admin.ui.theme.SmartSpendTheme
import com.example.smartspend.data.UserRepository
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AdminDashbordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartSpendTheme {
                SetBarColor(color = Color(0xff009177))
                var intent = Intent(this, CreateUserActivity::class.java)
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){

                    val viewModel : RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel( )
                    val email by viewModel.email.collectAsState()
                    val password by viewModel.password.collectAsState()
                    val isLoading by viewModel.isLoading.collectAsState()

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator()
                        } else {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                OutlinedTextField(
                                    value = email,
                                    onValueChange = viewModel::onEmailChanged,
                                    label = { Text("Email") },
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                OutlinedTextField(
                                    value = password,
                                    onValueChange = viewModel::onPasswordChanged,
                                    label = { Text("Password") },
                                    visualTransformation = PasswordVisualTransformation(),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(24.dp))

                                Button(
                                    onClick = viewModel::registerUser,
                                    modifier = Modifier.fillMaxWidth()


                                ) {
                                    Text("Register")
                                }
                                if (UserRepository.isLogin() == "true"){
                                    startActivity(intent)
                                }
                                if (UserRepository.isLogin() == "false"){
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}

@Composable
fun Greeting(intent: Intent) {
    Surface(){
        Column {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview3() {
    SmartSpendTheme {
    }
}

class RegisterViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }


    fun registerUser() {
        _isLoading.value = true

        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                _isLoading.value = false

                if (task.isSuccessful) {
                    // Set registered user data it to repository
                    UserRepository.setRegisteredUser(email.value.toString())
                    UserRepository.setLogin("true")
                    _email.value = ""
                    _password.value = ""

                } else {
                    // Registration failed
                    // Handle the error
                    UserRepository.setLogin("false")
                }
            }
    }
}