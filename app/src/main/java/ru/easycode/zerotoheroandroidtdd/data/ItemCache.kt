package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemCache(
    @PrimaryKey val id: Long,
    val text: String
)

