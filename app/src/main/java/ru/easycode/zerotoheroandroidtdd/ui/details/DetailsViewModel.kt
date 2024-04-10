package ru.easycode.zerotoheroandroidtdd.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.easycode.zerotoheroandroidtdd.core.livedata.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.data.ItemUi
import ru.easycode.zerotoheroandroidtdd.data.Repository

class DetailsViewModel(
    val changeLiveDataWrapper: ListLiveDataWrapper.All,
    private val repository: Repository.Change,
    private val clear: ClearViewModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherMain: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData
    fun init(itemId: Long) {
        scope.launch(dispatcher) {
            val item = repository.item(itemId)
            _liveData.postValue(item.text)
        }
    }

    fun delete(itemId: Long) {
        scope.launch(dispatcher) {
            repository.delete(itemId)
            withContext(dispatcherMain) {
                changeLiveDataWrapper.delete(ItemUi(itemId, liveData.value.toString()))
                comeback()
            }
        }
    }

    fun update(itemId: Long, newText: String) {
        scope.launch(dispatcher){
            repository.update(itemId, newText)
            withContext(dispatcherMain) {
                changeLiveDataWrapper.update(ItemUi(itemId, newText))
                comeback()
            }
        }
    }

    fun comeback() {
        clear.clear(DetailsViewModel::class.java)
    }
}
