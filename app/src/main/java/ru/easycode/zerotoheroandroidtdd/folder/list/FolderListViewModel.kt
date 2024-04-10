package ru.easycode.zerotoheroandroidtdd.folder.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderUi

class FolderListViewModel(
    repository: FoldersRepository.ReadList,
    listLiveDataWrapper: FolderListLiveDataWrapper.UpdateListAndRead,
    folderLiveDataWrapper: FolderLiveDataWrapper.Update,
    navigation: Navigation.Update,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    fun init() {
        TODO("Not yet implemented")
    }

    fun folderDetails(folderUi: FolderUi) {
        TODO("Not yet implemented")
    }

    fun addFolder() {
        TODO("Not yet implemented")
    }
}
