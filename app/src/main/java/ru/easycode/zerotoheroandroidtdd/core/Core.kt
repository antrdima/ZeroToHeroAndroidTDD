package ru.easycode.zerotoheroandroidtdd.core

import android.content.Context
import ru.easycode.zerotoheroandroidtdd.db.AppDataBase
import ru.easycode.zerotoheroandroidtdd.folder.core.db.FoldersDao
import ru.easycode.zerotoheroandroidtdd.note.core.db.NotesDao

interface Core {
    val foldersDao: FoldersDao
    val notesDao: NotesDao
}

class CoreImpl(private val context: Context) : Core {
    private val db: AppDataBase by lazy { AppDataBase.getInstance(context) }
    override val foldersDao = db.foldersDao()
    override val notesDao = db.notesDao()
}
