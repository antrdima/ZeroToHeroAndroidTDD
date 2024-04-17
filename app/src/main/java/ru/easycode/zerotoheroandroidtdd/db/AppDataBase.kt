package ru.easycode.zerotoheroandroidtdd.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderCache
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteCache
import ru.easycode.zerotoheroandroidtdd.folder.core.db.FoldersDao
import ru.easycode.zerotoheroandroidtdd.note.core.db.NotesDao

@Database(entities = [FolderCache::class, NoteCache::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun foldersDao(): FoldersDao
    abstract fun notesDao(): NotesDao

    companion object {

        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java, "Sample.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
