package ru.easycode.zerotoheroandroidtdd.folder.core

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderCache

@Dao
interface FoldersDao {
    @Insert
    suspend fun insert(folder: FolderCache)

    @Query("SELECT * FROM FolderCache")
    suspend fun folders(): List<FolderCache>

    @Query("DELETE FROM FolderCache WHERE id = :folderId")
    suspend fun delete(folderId: Long)

    @Update
    fun update(folderCache : FolderCache)
}
