package fr.hubert.gaming.view

import CreateGameViewModel
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import fr.hubert.gaming.databinding.ActivityCreateGameBinding

class CreateGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateGameBinding
    // Utilisation de 'viewModels()' nécessite 'activity-ktx' dépendance
    private val viewModel: CreateGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateGameBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val sharedPrefs = getSharedPreferences("YourAppPrefs", Context.MODE_PRIVATE)
        val creatorId = sharedPrefs.getInt("USER_ID", -1) // Utilisez -1 comme valeur par défaut

        binding.buttonAddGame.setOnClickListener {
            val name = binding.editTextGameName.text.toString()
            val description = binding.editTextGameDescription.text.toString()
            if (creatorId != -1) {
                viewModel.addGame(name, description, creatorId)
                // Informer l'utilisateur que le jeu a été ajouté et fermer l'activité
                finish()
            } else {
                // Gérez le cas où l'ID utilisateur n'est pas trouvé
            }
        }

//        binding.buttonAddGame.setOnClickListener {
//            val name = binding.editTextGameName.text.toString()
//            val description = binding.editTextGameDescription.text.toString()
//            // TODO: Récupérez l'ID de l'utilisateur connecté correctement
//            val creatorId = 1 // Exemple statique, remplacez par la récupération dynamique de l'ID
//            viewModel.addGame(name, description, creatorId)
//            // Informer l'utilisateur que le jeu a été ajouté et fermer l'activité
//            finish()
//        }

        binding.buttonCancel.setOnClickListener {
            // Fermer simplement l'activité sans ajouter de jeu
            finish()
        }
    }
}
