package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.base.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.base.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.ui.UiState

interface FakeLiveDataWrapper : LiveDataWrapper.Mutable {

    fun checkUpdateCalls(expected: List<UiState>)

    class Base : FakeLiveDataWrapper {

        private val actualCallsList = mutableListOf<UiState>()

        override fun checkUpdateCalls(expected: List<UiState>) {
            assertEquals(expected, actualCallsList)
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            bundleWrapper.save(actualCallsList.last())
        }

        override fun update(value: UiState) {
            actualCallsList.add(value)
        }

        override fun liveData(): LiveData<UiState> {
            throw IllegalStateException("not used in test")
        }
    }
}