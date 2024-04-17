package ru.easycode.zerotoheroandroidtdd.folder.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderCache

@Dao
interface FoldersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(folder: FolderCache)

    @Query("SELECT * FROM FolderCache")
    suspend fun folders(): List<FolderCache>

    @Query("DELETE FROM FolderCache WHERE id = :folderId")
    suspend fun delete(folderId: Long)
}
