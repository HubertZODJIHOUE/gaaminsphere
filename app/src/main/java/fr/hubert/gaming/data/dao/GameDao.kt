package fr.hubert.gaming.data.dao

import androidx.room.*
import fr.hubert.gaming.data.entity.Game

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(game: Game): Long

    @Query("SELECT * FROM games WHERE creatorId = :creatorId")
    suspend fun getGamesByCreator(creatorId: Int): List<Game>

    @Update
    suspend fun updateGame(game: Game)

    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun deleteGameById(gameId: Int)

    @Delete
    suspend fun deleteGame(game: Game)
}
