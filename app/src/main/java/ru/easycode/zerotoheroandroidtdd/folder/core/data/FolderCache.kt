package ru.easycode.zerotoheroandroidtdd.folder.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FolderCache(
    @PrimaryKey val id: Long,
    val text: String
)