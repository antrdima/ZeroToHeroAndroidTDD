package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModels
import ru.easycode.zerotoheroandroidtdd.core.Core
import ru.easycode.zerotoheroandroidtdd.core.CoreImpl
import ru.easycode.zerotoheroandroidtdd.core.ProvideViewModel

class App : Application(), ProvideViewModel {

    private lateinit var core: Core
    private lateinit var provideViewModel: ProvideViewModel
    private lateinit var viewModelFactory: ProvideViewModel.Factory

    private val clearViewModel = object : ClearViewModels {
        override fun clear(vararg viewModelClasses: Class<out ViewModel>) {
            viewModelFactory.clear(*viewModelClasses)
        }
    }

    override fun onCreate() {
        super.onCreate()
        core = CoreImpl(applicationContext)
        provideViewModel = ProvideViewModel.Base(core, clearViewModel)
        viewModelFactory = ProvideViewModel.Factory(provideViewModel)
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        return viewModelFactory.viewModel(clazz)
    }
}