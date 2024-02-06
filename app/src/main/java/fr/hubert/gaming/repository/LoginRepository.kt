package fr.hubert.gaming.repository

import fr.hubert.gaming.network.LoginService

class LoginRepository(private val loginService: LoginService) {
    suspend fun login(username: String, password: String) = loginService.login(username, password)
}
