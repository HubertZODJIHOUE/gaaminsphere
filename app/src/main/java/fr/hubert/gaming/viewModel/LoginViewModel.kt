package fr.hubert.gaming.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hubert.gaming.model.LoginResult
import fr.hubert.gaming.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    // LiveData pour observer les changements de l'état de connexion dans l'UI
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = loginRepository.login(username, password)

                Log.d("LoginResponse", "Réponse réussieaaaaaaaaa@@@ : ${response.toString()}")

                // Supposons que vous traitiez la réponse ici pour déterminer le succès
                //_loginResult.postValue(LoginResult(success = true, message = "Connexion réussie"))
            } catch (e: Exception) {
               // _loginResult.postValue(LoginResult(success = false, message = "Échec de la connexion: ${e.message}"))
            }
        }
    }
}


// Classe pour représenter le résultat de la tentative de connexion

