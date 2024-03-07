package com.example.meditation.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "Password"

@Composable
fun LoginScreen(modifier: Modifier = Modifier,navigateToHome:()->Unit={},auth:FirebaseAuth) {

}



/*fun createAccount(email: String, password: String,auth: FirebaseAuth) {
    // [START create_user_with_email]
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    MainActivity.appContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                updateUI(null)
            }
        }
    // [END create_user_with_email]
}

private fun signIn(email: String, password: String,auth: FirebaseAuth) {
    // [START sign_in_with_email]
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    MainActivity.appContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                updateUI(null)
            }
        }
    // [END sign_in_with_email]
}

fun updateUI(user: FirebaseUser?){}*/
