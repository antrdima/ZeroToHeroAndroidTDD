package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

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
}
