package ru.easycode.zerotoheroandroidtdd.note.core.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteUi

interface NoteLiveDataWrapper {
    fun update(noteText: String)

    fun liveData() : LiveData<String>

    class Base(private val liveData: MutableLiveData<String> = MutableLiveData()) :
        NoteLiveDataWrapper {
        override fun update(noteText: String) {
            liveData.value = noteText
        }

        override fun liveData(): LiveData<String>  {
            return liveData
        }
    }
}
