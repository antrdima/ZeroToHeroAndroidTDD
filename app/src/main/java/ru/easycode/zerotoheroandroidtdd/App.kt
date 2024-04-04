package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.core.viewmodel.ViewModelFactory

class App : Application(), ProvideViewModel {

    private var clearViewModel = object : ClearViewModel {
        override fun clear(viewModelClass: Class<out ViewModel>) {
            viewModelFactory.clear(viewModelClass)
        }
    }
    private lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        viewModelFactory =
            ViewModelFactory.Base(ProvideViewModel.Base(clearViewModel, applicationContext))
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return viewModelFactory.viewModel(viewModelClass)
    }
}