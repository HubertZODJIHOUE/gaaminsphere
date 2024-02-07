package fr.hubert.gaming.view

import fr.hubert.gaming.viewModel.LoginViewModel
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.hubert.gaming.R
import fr.hubert.gaming.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
         val usernameEditText= findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.sign_Button)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.loginUserRoom(username, password, this)
            } else {
                Toast.makeText(this, "Les champs ne peuvent pas être vides.", Toast.LENGTH_LONG).show()
            }
//            loginViewModel.login(username, password)
//            loginViewModel.loginResult.
//

//            loginViewModel.loginResult.observe(this, Observer { loginResult ->
//                if (loginResult != null) {
//                    Log.e("LoginActivity", "Résultat de la connexion dfdddfdffdfd : $loginResult")
//
////                    val messageToShow = if (loginResult.success) {
////                        "Connexion réussie : ${loginResult.message}"
////                    } else {
////                        "Échec de la connexion : ${loginResult.message}"
////                    }
//
////                    val messageToShow = if (loginResult.success) {
////                        "Connexion réussie : ${loginResult.message}"
////                    } else {
////                        "Échec de la connexion : ${loginResult.message}"
////                    }
//                    // Afficher le message dans un Toast
////                    Toast.makeText(this, messageToShow, Toast.LENGTH_SHORT).show()
////                    if (loginResult.success) {
////                        // Gérer le succès du login, par exemple en naviguant vers une autre activité
////                        navigateToHomeScreen()
////                    } else {
////                        // Afficher un message d'erreur à l'utilisateur
////                        showLoginFailed(loginResult.message)
////                    }
//                }
//            })
        }
        val createAccountText: TextView = findViewById(R.id.createAccount)

        // Définir un écouteur de clics sur le TextView
        createAccountText.setOnClickListener {
            // Créer une intention pour démarrer l'activité de création de compte
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
    private fun navigateToHomeScreen() {
        Toast.makeText(this, "Login attempt with", Toast.LENGTH_SHORT).show()
        // Navigation vers l'écran d'accueil ou une autre activité
    }

    private fun showLoginFailed(errorString: String?) {
        // Afficher un Toast ou une Snackbar avec le message d'erreur
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}