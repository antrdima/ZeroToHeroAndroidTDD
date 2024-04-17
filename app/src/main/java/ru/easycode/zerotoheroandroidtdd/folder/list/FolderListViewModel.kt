package ru.easycode.zerotoheroandroidtdd.folder.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.core.livedata.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.livedata.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.db.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderUi
import ru.easycode.zerotoheroandroidtdd.folder.create.CreateFolderScreen
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsScreen

class FolderListViewModel(
    private val repository: FoldersRepository.ReadList,
    val listLiveDataWrapper: FolderListLiveDataWrapper.UpdateListAndRead,
    val folderLiveDataWrapper: FolderLiveDataWrapper.Update,
    private val navigation: Navigation.Update,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun init() {
        scope.launch(dispatcher) {
            val folders = repository.folders().map { FolderUi(it.id, it.title, it.notesCount) }
            withContext(dispatcherMain) {
                listLiveDataWrapper.update(folders)
            }
        }
    }

    fun addFolder() {
        navigation.update(CreateFolderScreen)
    }

    fun folderDetails(folderUi: FolderUi) {
        folderLiveDataWrapper.update(folderUi)
        navigation.update(FolderDetailsScreen)
    }
}
