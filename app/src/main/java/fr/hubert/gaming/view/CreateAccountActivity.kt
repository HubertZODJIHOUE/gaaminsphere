package fr.hubert.gaming.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import fr.hubert.gaming.R

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        val alreadyMemberText: TextView = findViewById(R.id.alreadyMemberText)
        alreadyMemberText.setOnClickListener {
            // Intent pour naviguer vers l'écran de connexion
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val signUpButton: Button = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            // Ici, vous pouvez ajouter la logique pour créer le compte
        }
    }
}