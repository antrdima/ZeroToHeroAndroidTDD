package ru.easycode.zerotoheroandroidtdd.folder.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.FakeClear
import ru.easycode.zerotoheroandroidtdd.core.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.core.Navigation
import ru.easycode.zerotoheroandroidtdd.folder.core.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListLiveDataWrapper

class   CreateFolderViewModel(
    repository: FoldersRepository.Create,
    liveDataWrapper: FolderListLiveDataWrapper.Create,
    navigation: Navigation.Update,
    clear: ClearViewModels,
    dispatcher: CoroutineDispatcher,
    dispatcherMain: CoroutineDispatcher
) : ViewModel(){
    fun comeback() {
        TODO("Not yet implemented")
    }

    fun createFolder(name: String) {
        TODO("Not yet implemented")
    }
}
