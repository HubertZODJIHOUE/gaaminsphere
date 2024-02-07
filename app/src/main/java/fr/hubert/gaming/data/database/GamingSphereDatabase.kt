package fr.hubert.gaming.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.hubert.gaming.data.dao.GameDao
import fr.hubert.gaming.data.dao.UserDao
import fr.hubert.gaming.data.entity.Game
import fr.hubert.gaming.data.entity.User

@Database(entities = [User::class, Game::class], version = 1, exportSchema = false)
abstract class GamingSphereDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun gameDao(): GameDao
    companion object {
        @Volatile
        private var INSTANCE: GamingSphereDatabase? = null

        fun getDatabase(context: Context): GamingSphereDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GamingSphereDatabase::class.java,
                    "GaminDataBase"
                )
                    // MigrationStrategy ici si nécessaire
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // Retourner l'instance
                instance
            }
        }
    }

}
