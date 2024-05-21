package com.example.smartspend

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.Screens.home.HomeActivity
import com.example.smartspend.data.UserData
import com.example.smartspend.data.UserRepository
import com.example.smartspend.firebase.AuthViewModel
import com.example.smartspend.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

class LoginScreenActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartSpendTheme {
                SetBarColor(color = Color(0xff009177))
                val intentAccountSetup = Intent(this, AccountSetActivity::class.java)
                val intentHomeActivity = Intent(this, HomeActivity::class.java)


                // Initialize the UserData object
                var currentUser:UserData = UserData()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xff009177)
                ) {
                    // Create an instance of the AuthViewModel using the Factory
                    val viewModel: AuthViewModel by viewModels {
                        AuthViewModel.Factory(applicationContext)
                    }

                    // State variable to track whether the reset password screen should be shown
                    var showResetPasswordScreen by remember { mutableStateOf(false) }

                    val passwordVisibility =
                        remember { mutableStateOf(false) } // State to control password visibility
                    var username by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    val loginState by viewModel.loginState.collectAsState()
                    val context = LocalContext.current

                    val focusManager: FocusManager = LocalFocusManager.current

                    if (showResetPasswordScreen) {
                        ResetPasswordScreen(
                            onBackPressed = { showResetPasswordScreen = false },
                            viewModel = viewModel
                        )
                    } else {

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
                                fontSize = 100.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                            Text(
                                text = "S",
                                color = Color.Green,
                                fontSize = 100.sp,
                                fontWeight = FontWeight.ExtraBold,
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
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("example@smartspend.com") }
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
                                    Icon(
                                        imageVector = image,
                                        contentDescription = "Toggle Password Visibility"
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            onClick = {
                                if (LoginValidation(password, username, context)) {
                                    viewModel.signIn(username, password)
                                } else {

                                }


                                // Hide the keyboard
                                focusManager.clearFocus()


                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(15.dp))
                                .width(200.dp)
                                .background(color = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Login")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        when (loginState) {
                            AuthViewModel.LoginState.Loading -> {
                                CircularProgressIndicator()
                            }

                            is AuthViewModel.LoginState.Error -> {
                                val errorMessage =
                                    (loginState as AuthViewModel.LoginState.Error).message
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


                                // Navigate to the AccountSetUp screen
                                // Show a toast for successful login
                                LaunchedEffect(Unit) {
                                    showToast(context, "Login Successful")
                                }

                                // Fetch the user's data from Firestore
                                val db = FirebaseFirestore.getInstance()
                                val userDocRef = db.collection("Users").document(user.email ?: "")

                                userDocRef.get()
                                    .addOnSuccessListener { document ->
                                        if (document != null) {

                                            val firstName =
                                                document.data?.get("firstName") as? String
                                            val userModel = UserData(firstName)

                                            currentUser.firstName = firstName
                                            currentUser.username = user.email

                                            UserRepository.setEmail(user.email.toString())

                                            UserRepository.addUser(currentUser)

                                            //Check if the user has account information
                                            val accountNumber =
                                                document.data?.get("accountNumber") as? String
                                            val cardNumber =
                                                document.data?.get("cardNumber") as? String
                                            val expMonth = document.data?.get("expMonth") as? String
                                            val expYear = document.data?.get("expYear") as? String
                                            val cvv = document.data?.get("cvv") as? String

                                            if ((accountNumber != null && cardNumber != null && expMonth != null && expYear != null && cvv != null) && (accountNumber != "" && cardNumber != "" && expMonth != "" && expYear != "" && cvv != "")) {
                                                try {
                                                    setTransection(user.email.toString())
                                                } catch (e: Exception) {

                                                }
                                                // User has account information, navigate to DashboardActivity
                                                startActivity(intentHomeActivity)
                                            } else {
                                                // User doesn't have account information, navigate to AccountSetActivity
                                                startActivity(intentAccountSetup)
                                            }


                                        } else {
                                            Log.d(TAG, "No such document")
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.d(TAG, "get failed with ", exception)
                                    }


                            }

                            else -> {}

                        }


                        Spacer(modifier = Modifier.height(16.dp))

                        Row {
                            Text("Forgot Password? ", color = Color.White)
                            Text(
                                text = buildAnnotatedString {
                                    append("Reset Password")
                                    addStyle(
                                        style = SpanStyle(
                                            color = Color.Yellow,
                                            textDecoration = TextDecoration.Underline

                                        ),
                                        start = 0,
                                        end = "Reset Password".length
                                    )
                                },
                                modifier = Modifier.clickable(onClick = { showResetPasswordScreen = true })
                            )
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

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}

private suspend fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    // Delay for demonstration purposes
    delay(1000)
    println("Toast: $message")
}