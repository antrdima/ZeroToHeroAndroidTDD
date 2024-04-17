package ru.easycode.zerotoheroandroidtdd.note.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteCache

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteCache)
    @Query("SELECT * FROM NoteCache WHERE folderId = :folderId")
    suspend fun notes(folderId: Long): List<NoteCache>
    @Query("SELECT * FROM NoteCache where id = :noteId")
    suspend fun note(noteId: Long): NoteCache
    @Query("DELETE FROM NoteCache WHERE id = :noteId")
    suspend fun delete(noteId: Long)
    @Query("DELETE FROM NoteCache WHERE folderId = :folderId")
    suspend fun deleteByFolderId(folderId: Long)
}
