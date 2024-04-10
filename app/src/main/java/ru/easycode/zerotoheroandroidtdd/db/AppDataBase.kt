package ru.easycode.zerotoheroandroidtdd.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderCache
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteCache
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersDao
import ru.easycode.zerotoheroandroidtdd.note.core.NotesDao

@Database(entities = [FolderCache::class, NoteCache::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun foldersDao(): FoldersDao
    abstract fun notesDao(): NotesDao
}
