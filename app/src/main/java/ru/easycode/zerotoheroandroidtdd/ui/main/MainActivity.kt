package ru.easycode.zerotoheroandroidtdd.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import ru.easycode.zerotoheroandroidtdd.App
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.ui.add.AddBottomSheet
import ru.easycode.zerotoheroandroidtdd.ui.delete.DeleteBottomSheet

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val itemOnClickListener = object : ClickListener {
        override fun onClick(id: Long) {
            val deleteSheet = DeleteBottomSheet()
            val bundle = bundleOf().apply { putLong("KEY", id) }
            deleteSheet.arguments = bundle
            deleteSheet.show(supportFragmentManager, DeleteBottomSheet.TAG)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as App).viewModel(MainViewModel::class.java)

        val adapter = ListAdapter(itemOnClickListener)
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

    interface ClickListener {
        fun onClick(id: Long)
    }
}