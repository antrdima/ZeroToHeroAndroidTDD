package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {
    @Query("SELECT * FROM ItemCache")
    fun list(): List<ItemCache>

    @Insert
    fun add(item: ItemCache) : Long

    @Query("SELECT * FROM ItemCache WHERE id = :id LIMIT 1")
    fun item(id: Long): ItemCache

    @Query("DELETE FROM ItemCache WHERE id = :id")
    fun delete(id: Long)
}
