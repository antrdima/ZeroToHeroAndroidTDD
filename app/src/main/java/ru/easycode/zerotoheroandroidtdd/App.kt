package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.core.ViewModelFactory

class App : Application(), ProvideViewModel{

    private lateinit var factory: ViewModelFactory
    private val clearViewModel = object : ClearViewModel {
        override fun clear(viewModelClass: Class<out ViewModel>) = factory.clear(viewModelClass)
    }

    override fun onCreate() {
        super.onCreate()
        factory = ViewModelFactory.Base(ProvideViewModel.Base(clearViewModel))

    }
    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return factory.viewModel(viewModelClass)
    }
}
