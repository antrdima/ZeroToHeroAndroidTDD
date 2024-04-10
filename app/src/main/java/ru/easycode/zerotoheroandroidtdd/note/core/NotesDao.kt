package ru.easycode.zerotoheroandroidtdd.note.core

import androidx.room.Dao
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteCache

@Dao
interface NotesDao {
    suspend fun insert(note: NoteCache)
    suspend fun notes(folderId: Long): List<NoteCache>
    suspend fun note(noteId: Long): NoteCache
    suspend fun delete(noteId: Long)
    suspend fun deleteByFolderId(folderId: Long)
}
