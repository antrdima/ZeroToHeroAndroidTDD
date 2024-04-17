package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.folder.core.livedata.FolderLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.core.db.FoldersRepository
import ru.easycode.zerotoheroandroidtdd.folder.create.CreateFolderViewModel
import ru.easycode.zerotoheroandroidtdd.folder.details.FolderDetailsViewModel
import ru.easycode.zerotoheroandroidtdd.note.core.livedata.NoteListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.edit.EditFolderViewModel
import ru.easycode.zerotoheroandroidtdd.folder.core.livedata.FolderListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FolderListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.note.core.db.NotesRepository
import ru.easycode.zerotoheroandroidtdd.note.core.livedata.NoteLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.note.create.CreateNoteViewModel
import ru.easycode.zerotoheroandroidtdd.note.edit.EditNoteViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clazz: Class<T>): T

    class Factory(private val provideViewModel: ProvideViewModel) : ProvideViewModel,
        ClearViewModels {

        private val data = HashMap<Class<out ViewModel>, ViewModel>()

        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return if (data.containsKey(clazz)) {
                data[clazz] as T
            } else {
                val result = provideViewModel.viewModel(clazz)
                data[clazz] = result
                result
            }
        }

        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            for (clazz in viewModelClasses) {
                data.remove(clazz)
            }
        }
    }

    class Base(private val core: Core, private val clearViewModels: ClearViewModels) :
        ProvideViewModel {

        private val now = Now.Base()
        private val foldersRepository = FoldersRepository.Base(now, core.foldersDao, core.notesDao)
        private val notesRepository = NotesRepository.Base(now, core.notesDao)
        private val navigation = Navigation.Base()
        private val folderLiveDataWrapper = FolderLiveDataWrapper.Base()
        private val foldersListLiveDataWrapper = FolderListLiveDataWrapper.Base()
        private val noteLiveDataWrapper = NoteLiveDataWrapper.Base()
        private val noteListLiveDataWrapper = NoteListLiveDataWrapper.Base()

        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return when (clazz) {
                CreateFolderViewModel::class.java -> CreateFolderViewModel(
                    foldersRepository,
                    foldersListLiveDataWrapper,
                    navigation,
                    clearViewModels
                )

                FolderDetailsViewModel::class.java -> FolderDetailsViewModel(
                    notesRepository,
                    noteListLiveDataWrapper,
                    folderLiveDataWrapper,
                    navigation,
                    clearViewModels
                )

                EditFolderViewModel::class.java -> EditFolderViewModel(
                    folderLiveDataWrapper,
                    foldersRepository,
                    navigation,
                    clearViewModels
                )

                FolderListViewModel::class.java -> FolderListViewModel(
                    foldersRepository,
                    foldersListLiveDataWrapper,
                    folderLiveDataWrapper, navigation
                )

                MainViewModel::class.java -> MainViewModel(navigation)

                CreateNoteViewModel::class.java -> CreateNoteViewModel(
                    folderLiveDataWrapper,
                    noteListLiveDataWrapper,
                    notesRepository,
                    navigation,
                    clearViewModels
                )

                EditNoteViewModel::class.java -> EditNoteViewModel(
                    folderLiveDataWrapper,
                    noteLiveDataWrapper,
                    noteListLiveDataWrapper,
                    notesRepository,
                    navigation,
                    clearViewModels
                )

                else -> {
                    throw UnsupportedOperationException()
                }
            } as T
        }
    }
}
