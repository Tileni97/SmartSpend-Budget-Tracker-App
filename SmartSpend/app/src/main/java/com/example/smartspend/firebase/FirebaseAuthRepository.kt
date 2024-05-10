package com.example.smartspend.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

/**
 * Repository class to handle Firebase Authentication operations.
 */
class FirebaseAuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Signs in the user with the given email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return The signed-in FirebaseUser object, or null if sign-in failed.
     */
    suspend fun signIn(email: String, password: String): FirebaseUser? {
        return try {
            auth.signInWithEmailAndPassword(email, password).await().user
        } catch (e: Exception) {
            null
        }
    }
}