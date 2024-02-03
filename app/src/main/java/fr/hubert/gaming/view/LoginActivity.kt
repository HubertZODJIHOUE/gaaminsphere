package fr.hubert.gaming.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import fr.hubert.gaming.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
         val usernameEditText= findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            Toast.makeText(this, "Login attempt with $username", Toast.LENGTH_SHORT).show()
        }
        val createAccountText: TextView = findViewById(R.id.createAccountText)

        // Définir un écouteur de clics sur le TextView
        createAccountText.setOnClickListener {
            // Créer une intention pour démarrer l'activité de création de compte
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }
}