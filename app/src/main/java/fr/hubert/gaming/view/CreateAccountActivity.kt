package fr.hubert.gaming.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import fr.hubert.gaming.R
import fr.hubert.gaming.viewModel.CreateUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateAccountActivity : AppCompatActivity() {
    private val viewModel: CreateUserViewModel by  viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        val alreadyMemberText: TextView = findViewById(R.id.already_have_account)
        alreadyMemberText.setOnClickListener {
            // Intent pour naviguer vers l'écran de connexion
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val signUpButton: Button = findViewById(R.id.create_new_account)
        signUpButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.usernameEditText).text.toString()
            val password = findViewById<EditText>(R.id.passwordEditText).text.toString()
            viewModel.createUser(username, password, onSuccess = {
                // Naviguez vers la vue de connexion
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, onError = { error ->
                // Affichez l'erreur à l'utilisateur
//                Toast.(this, error, Toast.LENGTH_SHORT).show()
            })
        }
    }
}