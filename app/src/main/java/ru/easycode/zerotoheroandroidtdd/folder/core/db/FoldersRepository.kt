package ru.easycode.zerotoheroandroidtdd.folder.core.db

import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.folder.core.data.Folder
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderCache
import ru.easycode.zerotoheroandroidtdd.note.core.db.NotesDao

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


    class Base(
        private val now: Now,
        private val foldersDao: FoldersDao,
        private val notesDao: NotesDao
    ) : Create,
        Edit, ReadList {
        override suspend fun createFolder(name: String): Long {
            val id = now.timeInMillis()
            foldersDao.insert(FolderCache(id, name))
            return id
        }

        override suspend fun delete(folderId: Long) {
            foldersDao.delete(folderId)
            notesDao.deleteByFolderId(folderId)
        }

        override suspend fun rename(folderId: Long, newName: String) {
            foldersDao.insert(FolderCache(folderId, newName))
        }

        override suspend fun folders(): List<Folder> {
            return foldersDao.folders()
                .map { Folder(it.id, it.text, notesDao.notes(it.id).count()) }
        }
    }
}
