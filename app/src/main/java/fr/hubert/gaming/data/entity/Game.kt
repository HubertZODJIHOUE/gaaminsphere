package fr.hubert.gaming.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "games",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("creatorId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "creatorId") val creatorId: Int
)
