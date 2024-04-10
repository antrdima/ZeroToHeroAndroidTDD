package ru.easycode.zerotoheroandroidtdd.note.core

import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.note.core.data.MyNote


interface NotesRepository {
    fun createNote(noteId: Long, text: String)
    fun noteList(noteId: Long)
    fun deleteNote(noteId: Long)
    fun renameNote(noteId: Long, text: String)
    fun note(noteId: Long): MyNote



    interface Create {

        suspend fun createNote(folderId: Long, text: String): Long
    }

    class Base(private val now: Now, private val dao: NotesDao) : NotesRepository {
        override fun createNote(folderId: Long, text: String) {
            TODO("Not yet implemented")
        }

        override fun note(noteId: Long): MyNote {
            TODO("Not yet implemented")
        }

        override fun noteList(folderId: Long) {
            TODO("Not yet implemented")
        }

        override fun renameNote(noteId: Long, text: String) {
            TODO("Not yet implemented")
        }

        override fun deleteNote(noteId: Long) {
            TODO("Not yet implemented")
        }
    }

    interface Edit {

        suspend fun deleteNote(noteId: Long)
        suspend fun renameNote(noteId: Long, newName: String)
        suspend fun note(noteId: Long): MyNote
    }

    interface ReadList {

        suspend fun noteList(folderId: Long): List<MyNote>
    }
}