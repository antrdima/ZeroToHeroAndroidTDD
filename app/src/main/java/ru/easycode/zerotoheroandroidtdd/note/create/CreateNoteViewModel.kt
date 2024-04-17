package ru.easycode.zerotoheroandroidtdd.note.create

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
import ru.easycode.zerotoheroandroidtdd.note.core.db.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteUi

class CreateNoteViewModel(
    private val folderLiveDataWrapper: FolderLiveDataWrapper.Increment,
    private val addLiveDataWrapper: NoteListLiveDataWrapper.Create,
    private val repository: NotesRepository.Create,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun createNote(folderId: Long, text: String) {
        scope.launch(dispatcher) {
            val id = repository.createNote(folderId, text)
            withContext(dispatcherMain) {
                folderLiveDataWrapper.increment()
                addLiveDataWrapper.create(NoteUi(id, text, folderId))
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clear(CreateNoteViewModel::class.java)
        navigation.update(FolderDetailsScreen)
    }
}
