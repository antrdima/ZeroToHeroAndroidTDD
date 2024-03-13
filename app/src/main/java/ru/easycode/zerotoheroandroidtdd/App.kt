package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class App : Application() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        mainViewModel = MainViewModel(ListLiveDataWrapper.Base())
    }
}