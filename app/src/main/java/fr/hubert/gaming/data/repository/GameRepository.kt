package fr.hubert.gaming.data.repository

import fr.hubert.gaming.data.dao.GameDao
import fr.hubert.gaming.data.entity.Game

class GameRepository(private val gameDao: GameDao) {

    suspend fun insertGame(game: Game) = gameDao.insertGame(game)

    suspend fun getGamesByCreator(creatorId: Int) = gameDao.getGamesByCreator(creatorId)

    suspend fun updateGame(game: Game) = gameDao.updateGame(game)

    suspend fun deleteGame(game: Game) = gameDao.deleteGame(game)
}
