package ru.easycode.zerotoheroandroidtdd.folder.core.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.livedata.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderUi

interface FolderListLiveDataWrapper {

    interface Read{
        fun liveData(): LiveData<List<FolderUi>>
    }
    interface UpdateListAndRead : Read{
        fun update(list: List<FolderUi>)
    }

    interface Create {
        fun create(folderUi: FolderUi)
    }

    class Base(private val liveData: MutableLiveData<List<FolderUi>> = MutableLiveData()) :
        UpdateListAndRead, Create {
        override fun update(list: List<FolderUi>) {
            liveData.value = list
        }

        override fun liveData(): LiveData<List<FolderUi>> {
            return liveData
        }

        override fun create(folderUi: FolderUi) {
            liveData.value = (liveData.value ?: emptyList()) + folderUi
        }
    }
}
