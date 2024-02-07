package fr.hubert.gaming.data.dao

import androidx.room.*
import fr.hubert.gaming.data.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User):Long

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)

    @Delete
    suspend fun deleteUser(user: User)
}
