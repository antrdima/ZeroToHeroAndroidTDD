package ru.easycode.zerotoheroandroidtdd.note.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.core.livedata.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsScreen
import ru.easycode.zerotoheroandroidtdd.note.core.livedata.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.livedata.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.db.NotesRepository

class EditNoteViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Decrement,
    val noteLiveDataWrapper: NoteLiveDataWrapper,
    private val noteListLiveDataWrapper: NoteListLiveDataWrapper.Update,
    private val repository: NotesRepository.Edit,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun init(noteId: Long) {
        scope.launch(dispatcher){
            val note = repository.note(noteId)
            withContext(dispatcherMain){
                noteLiveDataWrapper.update(note.title)
            }
        }
    }

    fun deleteNote(noteId: Long) {
        scope.launch (dispatcher)        {
            repository.deleteNote(noteId)
            withContext(dispatcherMain){
                folderLiveDataWrapper.decrement()
                comeback()
            }
        }
    }

    fun renameNote(noteId: Long, newText: String) {
        scope.launch(dispatcher){
            repository.renameNote(noteId, newText)
            withContext(dispatcherMain){
                noteListLiveDataWrapper.update(noteId, newText)
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clear(EditNoteViewModel::class.java)
        navigation.update(FolderDetailsScreen)
    }
}
