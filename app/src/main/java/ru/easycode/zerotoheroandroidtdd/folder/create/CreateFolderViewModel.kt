package ru.easycode.zerotoheroandroidtdd.folder.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.core.db.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderUi
import ru.easycode.zerotoheroandroidtdd.folder.core.livedata.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FoldersListScreen

class CreateFolderViewModel(
    private val repository: FoldersRepository.Create,
    private val liveDataWrapper: FolderListLiveDataWrapper.Create,
    private val navigation: Navigation.Update,
    private val clear: ClearViewModels,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun createFolder(name: String) {
        scope.launch(dispatcher) {
            val id = repository.createFolder(name)
            withContext(dispatcherMain) {
                liveDataWrapper.create(FolderUi(id, name, 0))
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clear(CreateFolderViewModel::class.java)
        navigation.update(FoldersListScreen)
    }
}
