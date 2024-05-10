package com.example.smartspend.firebase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel to handle authentication-related operations and state.
 */
class AuthViewModel : ViewModel() {

    private val repository = FirebaseAuthRepository()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState: StateFlow<LoginState> = _loginState

    /**
     * Signs in the user with the given email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     */
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
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
}