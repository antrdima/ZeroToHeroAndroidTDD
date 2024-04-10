package ru.easycode.zerotoheroandroidtdd.folder.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository

class EditFolderViewModel(
    folderLiveDataWrapper: FolderLiveDataWrapper.Rename,
    repository: FoldersRepository.Edit,
    navigation: Navigation.Update,
    clear: ClearViewModels,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel() {
    fun comeback() {
        TODO("Not yet implemented")
    }

    fun deleteFolder(folderId: Long) {
        TODO("Not yet implemented")
    }

    fun renameFolder(folderId: Long, newName: String) {
        TODO("Not yet implemented")
    }
}
