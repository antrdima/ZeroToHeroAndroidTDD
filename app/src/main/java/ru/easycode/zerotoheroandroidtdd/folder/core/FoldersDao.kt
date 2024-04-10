package ru.easycode.zerotoheroandroidtdd.folder.core

import androidx.room.Dao
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderCache

@Dao
interface FoldersDao {
    suspend fun insert(folder: FolderCache)
    suspend fun folders(): List<FolderCache>
    suspend fun delete(folderId: Long)
}
