package ru.easycode.zerotoheroandroidtdd.ui.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.data.Repository

class AddViewModel(
    private val repository: Repository.Add,
    private val liveDataWrapper: ListLiveDataWrapper.Add,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun add(value: String) {
        scope.launch(dispatcher) {
            repository.add(value)
            liveDataWrapper.add(value)
            comeback()
        }
    }

    fun comeback() {
        clear.clear(this.javaClass)
    }
}
