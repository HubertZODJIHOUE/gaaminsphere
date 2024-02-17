package fr.hubert.gaming.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import fr.hubert.gaming.R
import fr.hubert.gaming.databinding.ActivityLoginBinding
import fr.hubert.gaming.viewModel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    /*** Lazy initialization of the LoginViewModel with delegation ***/
    private val loginViewModel: LoginViewModel by viewModel()

    /*** Declaration of the ViewBinding for activity_login.xml ***/
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*** Initializing ViewBinding ***/
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*** Adding text watchers to EditTexts for reactive input validation ***/
        addTextWatchers()

        /*** Setting up click listener for the sign-in button ***/
        binding.signButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            /*** Assuming validation is already done via TextWatchers ***/
            if (username.isNotEmpty() && password.isNotEmpty()) {
                /*** Initiating login process ***/
                loginViewModel.loginUserRoom(username, password, applicationContext)
                /*** Observing login result and notifying the user ***/
                observeLoginResult()
            }
        }
    }

    /*** Adds TextWatchers to EditTexts for reactive validation ***/
    private fun addTextWatchers() {
        binding.usernameEditText.addTextChangedListener(object : TextWatcher {
            /*** Callback before text changes ***/
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            /*** Callback as text is changing ***/
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            /*** Callback after text has changed ***/
            override fun afterTextChanged(s: Editable?) {
                /*** Validating username input ***/
                validateUsername()
            }
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            /*** Callback before text changes ***/
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            /*** Callback as text is changing ***/
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            /*** Callback after text has changed ***/
            override fun afterTextChanged(s: Editable?) {
                /*** Validating password input ***/
                validatePassword()
            }
        })
    }

    /*** Validates username and applies the appropriate background ***/
    private fun validateUsername() {
        val username = binding.usernameEditText.text.toString()
        if (username.isEmpty() || username.isBlank() || username.all { it.isDigit() }) {
            binding.usernameEditText.error = "Username cannot be empty or a number"
            binding.usernameEditText.setBackgroundResource(R.drawable.edit_text_error_background)
        } else {
            binding.usernameEditText.setBackgroundResource(R.drawable.edit_text_valid_background)
        }
    }

    /*** Validates password and applies the appropriate background ***/
    private fun validatePassword() {
        val password = binding.passwordEditText.text.toString()
        if (password.isEmpty() || password.length < 6) {
            binding.passwordEditText.error = "Password must be at least 6 characters"
            binding.passwordEditText.setBackgroundResource(R.drawable.edit_text_error_background)
        } else {
            binding.passwordEditText.setBackgroundResource(R.drawable.edit_text_valid_background)
        }
    }

    /*** Observes login result LiveData from the ViewModel ***/
    private fun observeLoginResult() {
        loginViewModel.loginResult.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                /*** Navigating to the game list activity on successful login ***/
                navigateToGameListeActivity()
            } else {
                /*** Showing toast message on login failure ***/
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /*** Navigates to GamesListeActivity ***/
    private fun navigateToGameListeActivity() {
        val intent = Intent(this, GamesListeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
