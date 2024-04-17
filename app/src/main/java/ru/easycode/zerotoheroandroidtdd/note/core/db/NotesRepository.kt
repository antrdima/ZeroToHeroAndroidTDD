package ru.easycode.zerotoheroandroidtdd.note.core.db

import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.note.core.data.MyNote
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteCache

interface NotesRepository {

    interface Create {
        suspend fun createNote(folderId: Long, text: String): Long
    }

    interface Edit {
        suspend fun deleteNote(noteId: Long)
        suspend fun renameNote(noteId: Long, newName: String)
        suspend fun note(noteId: Long): MyNote
    }

    interface ReadList {
        suspend fun noteList(folderId: Long): List<MyNote>
    }

    interface All : Create, Edit, ReadList
    class Base(private val now: Now, private val notesDao: NotesDao) : All {

        override suspend fun createNote(folderId: Long, text: String) : Long{
            val id = now.timeInMillis()
            notesDao.insert(NoteCache(id, text, folderId))
            return id
        }

        override suspend fun noteList(folderId: Long): List<MyNote> {
            return notesDao.notes(folderId).map { MyNote(it.id, it.text, it.folderId) }
        }

        override suspend fun deleteNote(noteId: Long) {
            notesDao.delete(noteId)
        }

        override suspend fun renameNote(noteId: Long, text: String) {
            val note = notesDao.note(noteId)
            notesDao.insert(NoteCache(noteId, text, note.folderId))
        }

        override suspend fun note(noteId: Long): MyNote {
            val noteCache = notesDao.note(noteId)
            return MyNote(noteCache.id, noteCache.text, noteCache.folderId)
        }
    }
}