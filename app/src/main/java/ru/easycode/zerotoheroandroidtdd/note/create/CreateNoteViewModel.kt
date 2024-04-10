package ru.easycode.zerotoheroandroidtdd.note.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.details.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository

class CreateNoteViewModel(
    folderLiveDataWrapper: FolderLiveDataWrapper.Increment,
    addLiveDataWrapper: NoteListLiveDataWrapper.Create,
    repository: NotesRepository.Create,
    navigation: Navigation.Update,
    clear: ClearViewModels,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    fun comeback() {
        TODO("Not yet implemented")
    }

    fun createNote(folderId: Long, text: String) {
        TODO("Not yet implemented")
    }
}
