package ru.easycode.zerotoheroandroidtdd.core.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import ru.easycode.zerotoheroandroidtdd.core.Now
import ru.easycode.zerotoheroandroidtdd.core.livedata.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.data.ItemsDataBase
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.ui.add.AddViewModel
import ru.easycode.zerotoheroandroidtdd.ui.details.DetailsViewModel
import ru.easycode.zerotoheroandroidtdd.ui.main.MainViewModel

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
    class Base(private val clearViewModel: ClearViewModel, applicationContext: Context) :
        ProvideViewModel {

        private lateinit var repository: Repository.All
        private lateinit var sharedLiveData: ListLiveDataWrapper.All

        init {
            val db =    Room.databaseBuilder(applicationContext,
                ItemsDataBase::class.java,
                "items_database")
                .fallbackToDestructiveMigration()
                .build()
            val dao = db.itemsDao()

            repository = Repository.Base(dao, Now.Base())
            sharedLiveData = ListLiveDataWrapper.Base()
        }

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {

            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel(
                    repository,
                    sharedLiveData
                )
                AddViewModel::class.java -> AddViewModel(
                    repository,
                    sharedLiveData,
                    clearViewModel
                )
                DetailsViewModel::class.java -> DetailsViewModel(
                    sharedLiveData,
                    repository,
                    clearViewModel
                )
                else -> throw IllegalStateException("viewModel")
            } as T
        }
    }
}

