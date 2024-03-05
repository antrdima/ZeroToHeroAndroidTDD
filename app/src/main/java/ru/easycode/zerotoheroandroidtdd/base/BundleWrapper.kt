package ru.easycode.zerotoheroandroidtdd.base

import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.ui.UiState

interface BundleWrapper {

    interface Save {
        fun save(uiState: UiState)
    }

    interface Restore {
        fun restore(): UiState
    }

    interface Mutable : Save, Restore

    class Base(private val bundle: Bundle) : Mutable {
        override fun save(uiState: UiState) {
            bundle.putSerializable("key", uiState)
        }

        override fun restore(): UiState {
            return bundle.getSerializable("key") as UiState
        }
    }
}
