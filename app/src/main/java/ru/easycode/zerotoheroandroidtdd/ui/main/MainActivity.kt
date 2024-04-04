package ru.easycode.zerotoheroandroidtdd.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.ui.add.AddBottomSheet

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as App).viewModel(MainViewModel::class.java)

        val adapter = ListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.liveData().observe(this) {
            adapter.update(it)
        }
        binding.addButton.setOnClickListener {
            val bottomSheet = AddBottomSheet()
            bottomSheet.show(supportFragmentManager, AddBottomSheet.TAG)
        }

        viewModel.init()
    }
}