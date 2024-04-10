package ru.easycode.zerotoheroandroidtdd.note.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.details.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository

class EditNoteViewModel(
    folderLiveDataWrapper: FolderLiveDataWrapper.Decrement,
    noteLiveDataWrapper: NoteLiveDataWrapper,
    noteListLiveDataWrapper: NoteListLiveDataWrapper.Update,
    repository: NotesRepository.Edit,
    navigation: Navigation.Update,
    clear: ClearViewModels,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    fun init(noteId: Long) {
        TODO("Not yet implemented")
    }

    fun deleteNote(noteId: Long) {
        TODO("Not yet implemented")
    }

    fun comeback() {
        TODO("Not yet implemented")
    }

    fun renameNote(noteId: Long, newText: String) {
        TODO("Not yet implemented")
    }
}
