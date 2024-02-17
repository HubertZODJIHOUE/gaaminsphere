package fr.hubert.gaming.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.hubert.gaming.data.repository.UserRepository
import fr.hubert.gaming.model.LoginResult
import fr.hubert.gaming.repository.LoginRepository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

/**we give two repository one for API : LoginRepository and userRoomrepository for  local room datatbase**/
class LoginViewModel(private val loginRepository: LoginRepository, private val  userRoomRepository: UserRepository) : ViewModel() {
    private val loginResultApi = MutableLiveData<Boolean>()
    public val loginResult = MutableLiveData<Boolean>()



    /**
     * Initiates a login request through the provided login service. This function is responsible for
     * communicating with the repository to send login credentials and process the response.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    private fun loginUserApi(username: String, password: String) {
        viewModelScope.launch {
            try {
                /** Performing the login request via the repository. */
                val response: Response<ResponseBody> = loginRepository.login(username, password)

                if (response.isSuccessful && response.body() != null) {
                    /** Assuming successful login if the response is successful and body is not null. */
                    Log.d("LoginResponse", "@@@@@@@@@@@Login successful: ${response.body().toString()}")
                    loginResultApi.postValue(true)
//                    Toast.makeText(this@LoginViewModel, "Login to API successful", Toast.LENGTH_SHORT).show()

                } else {
                    /** Considering any non-successful response as a login failure. */
                    Log.e(
                        "LoginResponse",
                        "Login failed: ${response.errorBody()?.string() ?: "Unknown error"}"
                    )
                    loginResultApi.postValue(false)
                }
            } catch (e: Exception) {
                /** Handling exceptions related to the network request. */
                Log.e("LoginError", "Login failure: ${e.message}")
                loginResult.postValue(false)
            }
        }
    }

    fun loginUserRoom(username: String, password: String, context: Context) = viewModelScope.launch {
        val user = userRoomRepository.getUserByUsername(username)
        if (user != null && user.password == password && user.username== username) {
            val sharedPrefs = context.getSharedPreferences("YourAppPrefs", Context.MODE_PRIVATE)
            with(sharedPrefs.edit()) {
                putInt("USER_ID", user.id)
                apply()
            }
            loginResult.postValue(true)
            loginUserApi(user.username, user.password)
        } else {
            loginResult.postValue(false)
        }
    }



}


// Classe pour représenter le résultat de la tentative de connexion

