package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.easycode.zerotoheroandroidtdd.base.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.data.SimpleService
import ru.easycode.zerotoheroandroidtdd.ui.MainViewModel

class App : Application() {

    lateinit var mainViewModel: MainViewModel

    fun initialize() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: SimpleService = retrofit.create(SimpleService::class.java)
        val url =
            "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
        val liveDataWrapper = LiveDataWrapper.Base()
        val repository = Repository.Base(service, url)

        mainViewModel = MainViewModel(liveDataWrapper, repository)
    }

    override fun onCreate() {
        super.onCreate()
        initialize()
    }
}