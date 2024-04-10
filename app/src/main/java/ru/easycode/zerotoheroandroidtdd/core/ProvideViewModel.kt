package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ProvideViewModel {
    fun <T : ViewModel> viewModel(clasz: Class<T>): T

    class Factory(provide: ProvideViewModel) : ProvideViewModel, ClearViewModels{
        override fun <T : ViewModel> viewModel(clasz: Class<T>): T {
            TODO("Not yet implemented")
        }
        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            TODO("Not yet implemented")
        }
    }
}
