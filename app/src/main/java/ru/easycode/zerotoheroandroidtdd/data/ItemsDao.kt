package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {
    @Query("SELECT * FROM ItemCache")
    fun list(): List<ItemCache>
    @Insert
    fun add(item: ItemCache)
}
