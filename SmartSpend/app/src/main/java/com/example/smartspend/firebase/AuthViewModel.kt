package com.example.smartspend.firebase

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel to handle authentication-related operations and state.
 */
class AuthViewModel(private val context: Context) : ViewModel() {


    private val repository = FirebaseAuthRepository()
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState

    private val _resetPasswordState = MutableStateFlow<ResetPasswordState>(ResetPasswordState.Initial)
    val resetPasswordState: StateFlow<ResetPasswordState> = _resetPasswordState


    /**
     * Signs in the user with the given email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     */
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            // Check for internet connection
            if (!isNetworkAvailable(context)) {
                _loginState.value = LoginState.Error("No internet connection")
                return@launch
            }

            // Proceed with sign-in logic
            val user = repository.signIn(email, password)
            _loginState.value = if (user != null) {
                LoginState.Success(user)
            } else {
                LoginState.Error("Invalid email or password")
            }
        }
    }

    /**
     * Sealed class to represent the different states of the login process.
     */
    sealed class LoginState {
        object Initial : LoginState()
        object Loading : LoginState()
        data class Success(val user: FirebaseUser) : LoginState()
        data class Error(val message: String) : LoginState()
    }


    /**
     * Factory for creating an instance of AuthViewModel with application context.
     */
    class Factory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            _resetPasswordState.value = ResetPasswordState.Loading

            // Check for internet connection
            if (!isNetworkAvailable(context)) {
                _resetPasswordState.value = ResetPasswordState.Error("No internet connection")
                return@launch
            }

            // Proceed with reset password logic
            repository.resetPassword(email)
                .addOnSuccessListener {
                    _resetPasswordState.value = ResetPasswordState.Success
                }
                .addOnFailureListener { exception ->
                    val errorMessage = when (exception) {
                        is FirebaseAuthException -> exception.message
                        else -> "An error occurred"
                    }
                    _resetPasswordState.value = ResetPasswordState.Error(errorMessage.toString())
                }
        }
    }
    sealed class ResetPasswordState {
        object Initial : ResetPasswordState()
        object Loading : ResetPasswordState()
        object Success : ResetPasswordState()
        data class Error(val message: String) : ResetPasswordState()
    }



}

/**
 * Helper function to check if the device has an active internet connection.
 */
private fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(android.net.NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(android.net.NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }


}