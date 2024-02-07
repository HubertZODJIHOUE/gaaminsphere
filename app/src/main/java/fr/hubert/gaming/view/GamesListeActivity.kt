package fr.hubert.gaming.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.hubert.gaming.R

class GamesListeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_liste)

        val gamesRecyclerView: RecyclerView = findViewById(R.id.gamesRecyclerView)
        gamesRecyclerView.layoutManager = LinearLayoutManager(this)
        // Configurez ici votre adapter pour le RecyclerView

        val fabAddGame: FloatingActionButton = findViewById(R.id.fab_add_game)
        fabAddGame.setOnClickListener {
            // Lancez ici votre activité de création de jeu
            val intent = Intent(this, CreateGameActivity::class.java)
            startActivity(intent)
        }
    }
}