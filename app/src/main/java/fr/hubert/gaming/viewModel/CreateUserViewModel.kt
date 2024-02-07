package fr.hubert.gaming.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hubert.gaming.data.entity.User
import fr.hubert.gaming.data.repository.UserRepository
import kotlinx.coroutines.launch

class CreateUserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun createUser(username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // Création d'une nouvelle instance de User
                val user = User(username = username, password = password)
                // Insertion de l'utilisateur dans la base de données
                val userId = userRepository.insertUser(user)
                // Si l'insertion est réussie et retourne un ID valide, on appelle onSuccess
                if (userId > 0) {
                    onSuccess()
                } else {
                    onError("Failed to create user")
                }
            } catch (e: Exception) {
                // En cas d'exception, on appelle onError avec le message d'erreur
                onError(e.message ?: "An error occurred")
            }
        }
    }
}
