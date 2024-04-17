package ru.easycode.zerotoheroandroidtdd.folder.details

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
import ru.easycode.zerotoheroandroidtdd.folder.edit.EditFolderScreen
import ru.easycode.zerotoheroandroidtdd.folder.list.FoldersListScreen
import ru.easycode.zerotoheroandroidtdd.note.core.livedata.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.core.db.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteUi
import ru.easycode.zerotoheroandroidtdd.note.create.CreateNoteScreen
import ru.easycode.zerotoheroandroidtdd.note.edit.EditNoteScreen

class FolderDetailsViewModel(
    private val noteListRepository: NotesRepository.ReadList,
    val noteListLiveDataWrapper: NoteListLiveDataWrapper.UpdateList,
    val folderLiveDataWrapper: FolderLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun init() {
        scope.launch(dispatcher){
            val folderId = folderLiveDataWrapper.folderId()
            val myNotes = noteListRepository.noteList(folderId).map{NoteUi(it.id, it.title, it.folderId)}
            withContext(dispatcherMain){
                noteListLiveDataWrapper.update(myNotes)
            }
        }
    }

    fun createNote() {
        val folderId = folderLiveDataWrapper.folderId()
        navigation.update(CreateNoteScreen(folderId))
    }

    fun editNote(noteUi: NoteUi) {
        navigation.update(EditNoteScreen(noteUi.id))
    }

    fun editFolder() {
        val folderId = folderLiveDataWrapper.folderId()
        navigation.update(EditFolderScreen(folderId))
    }

    fun comeback() {
        clear.clear(FolderDetailsViewModel::class.java)
        navigation.update(FoldersListScreen)
    }
}
