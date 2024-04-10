package ru.easycode.zerotoheroandroidtdd.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemsDao {
    @Query("SELECT * FROM ItemCache")
    fun list(): List<ItemCache>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: ItemCache) : Long
    @Query("SELECT * FROM ItemCache WHERE id = :id LIMIT 1")
    fun item(id: Long): ItemCache
    @Query("DELETE FROM ItemCache WHERE id = :id")
    fun delete(id: Long)
    @Update
    fun update(itemCache: ItemCache)
}
