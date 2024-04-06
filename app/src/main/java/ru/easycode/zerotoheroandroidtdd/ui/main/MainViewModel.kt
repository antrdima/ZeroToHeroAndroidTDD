package ru.easycode.zerotoheroandroidtdd.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.livedata.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.data.ItemUi
import ru.easycode.zerotoheroandroidtdd.data.Repository

class MainViewModel(
    private val repository: Repository.Read,
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), ListLiveDataWrapper.Read {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    override fun liveData(): LiveData<List<ItemUi>> = liveDataWrapper.liveData()

    fun init() {
        scope.launch(dispatcher) {
            liveDataWrapper.update(repository.list().map { ItemUi(it.id, it.text) })
        }
    }
}
