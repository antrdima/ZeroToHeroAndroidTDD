package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.CreateViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {

    class Base(private val provideViewModel: ProvideViewModel) : ViewModelFactory {

        private val data = HashMap<Class<out ViewModel>, ViewModel>()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return if (data.containsKey(viewModelClass)) {
                data[viewModelClass] as T
            } else {
                val result = provideViewModel.viewModel(viewModelClass)
                data[viewModelClass] = result
                result
            }
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            data.remove(viewModelClass)
        }
    }
}


interface ProvideViewModel {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T
    class Base(private val clearViewModel : ClearViewModel) : ProvideViewModel {
        private val navigation = Navigation.Base()
        private val sharedLiveData = ListLiveDataWrapper.Base()
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {

            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(navigation)
                ListViewModel::class.java -> ListViewModel(
                    sharedLiveData as ListLiveDataWrapper.Mutable,
                    navigation
                )
                CreateViewModel::class.java -> CreateViewModel(
                    sharedLiveData as ListLiveDataWrapper.Add,
                    navigation,
                    clearViewModel
                )
                else -> throw IllegalStateException("viewModel")
            } as T
        }
    }
}

interface ClearViewModel {
    fun clear(viewModelClass: Class<out ViewModel>)
}
