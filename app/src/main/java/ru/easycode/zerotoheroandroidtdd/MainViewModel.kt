package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper, private val repository: Repository
) : ProvideLiveData {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            repository.load()
            liveDataWrapper.update(UiState.ShowData)

        }
    }

    override fun liveData() = liveDataWrapper.liveData()
//
//
//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val savedStateHandle = createSavedStateHandle()
//                val myRepository = (this[APPLICATION_KEY] as MyApplication).myRepository
//                val liveDataWrapper =  this[CreationExtras.Key<LiveDataWrapper>] as LiveDataWrapper
//                MainViewModel(
//                    myRepository = myRepository,
//                    savedStateHandle = savedStateHandle
//                )
//            }
//        }
//    }
}
