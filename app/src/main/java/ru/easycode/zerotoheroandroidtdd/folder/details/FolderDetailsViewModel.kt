package ru.easycode.zerotoheroandroidtdd.folder.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteUi

class FolderDetailsViewModel(
    noteListRepository: NotesRepository.ReadList,
    liveDataWrapper: NoteListLiveDataWrapper.UpdateListAndRead,
    folderLiveDataWrapper: FolderLiveDataWrapper.Mutable,
    navigation: Navigation.Update,
    clear: ClearViewModels,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    fun init() {
        TODO("Not yet implemented")
    }

    fun createNote() {
        TODO("Not yet implemented")
    }

    fun editNote(noteUi: NoteUi) {
        TODO("Not yet implemented")
    }

    fun editFolder() {
        TODO("Not yet implemented")
    }

    fun comeback() {
        TODO("Not yet implemented")
    }
}
