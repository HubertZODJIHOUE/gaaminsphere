package fr.hubert.gaming.data.repository

import android.content.Context
import fr.hubert.gaming.data.database.GamingSphereDatabase
import fr.hubert.gaming.data.entity.User

class UserRepository(context: Context) {
    private val userDao = GamingSphereDatabase.getDatabase(context).userDao()

    suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}
