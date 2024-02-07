import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hubert.gaming.data.entity.Game
import fr.hubert.gaming.data.repository.GameRepository

import kotlinx.coroutines.launch

class CreateGameViewModel(private val gameRepository: GameRepository) : ViewModel() {

    // Fonction pour ajouter un jeu
    fun addGame(name: String, description: String, creatorId: Int) {
        val newGame = Game(name = name, description = description, creatorId = creatorId)
        viewModelScope.launch {
            gameRepository.insertGame(newGame)
        }
    }
}
