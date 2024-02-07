package fr.hubert.gaming.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hubert.gaming.data.repository.UserRepository
import fr.hubert.gaming.model.LoginResult
import fr.hubert.gaming.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository, private val  userRepository: UserRepository) : ViewModel() {
    // LiveData pour observer les changements de l'état de connexion dans l'UI
    private val _loginResult = MutableLiveData<LoginResult>()
//    val loginResult: LiveData<LoginResult> = _loginResult
private val loginResult = MutableLiveData<Boolean>()


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
    // Dans LoginViewModel
//    fun loginUserRoom(username: String, password: String, context: Context) = viewModelScope.launch {
//        userRepository.getUserByUsername(username)?.let { user ->
//            if (user.password == password) {
//                val sharedPrefs = context.getSharedPreferences("YourAppPrefs", Context.MODE_PRIVATE)
//                with(sharedPrefs.edit()) {
//                    putInt("USER_ID", user.id)
//                    apply()
//                }
//                // Gérez la navigation ou l'action de succès ici
//            } else {
//                // Gérez l'erreur de login ici
//            }
//        }
//    }

    fun loginUserRoom(username: String, password: String, context: Context) = viewModelScope.launch {
        val user = userRepository.getUserByUsername(username)
        if (user != null && user.password == password) {
            val sharedPrefs = context.getSharedPreferences("YourAppPrefs", Context.MODE_PRIVATE)
            with(sharedPrefs.edit()) {
                putInt("USER_ID", user.id)
                apply()
            }
            loginResult.postValue(true)
        } else {
            loginResult.postValue(false)
        }
    }


}


// Classe pour représenter le résultat de la tentative de connexion

