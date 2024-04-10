package ru.easycode.zerotoheroandroidtdd.folder.core

import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.folder.core.data.Folder
import ru.easycode.zerotoheroandroidtdd.note.core.NotesDao

interface FoldersRepository {
    interface Create {
        suspend fun createFolder(name: String): Long
    }

    interface Edit {
        suspend fun delete(folderId: Long)
        suspend fun rename(folderId: Long, newName: String)
    }

    interface ReadList {
        suspend fun folders(): List<Folder>
    }


    class Base(now: Now, foldersDao: FoldersDao, notesDao: NotesDao) : Create, Edit, ReadList{
        override suspend fun createFolder(name: String): Long {
            TODO("Not yet implemented")
        }

        override suspend fun delete(folderId: Long) {
            TODO("Not yet implemented")
        }

        override suspend fun rename(folderId: Long, newName: String) {
            TODO("Not yet implemented")
        }

        override suspend fun folders(): List<Folder> {
            TODO("Not yet implemented")
        }

    }
}
