package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var actionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTextView = findViewById(R.id.titleTextView)
        progressBar = findViewById(R.id.progressBar)
        actionButton = findViewById(R.id.actionButton)

        val liveDataWrapper = LiveDataWrapper.Base()

        val repository = Repository.Base()

        val viewModel = MainViewModel(liveDataWrapper, repository)
        actionButton.setOnClickListener {
            viewModel.load()
        }

        liveDataWrapper.liveData().observe(this) {
            it.apply(titleTextView, progressBar, actionButton)
        }
    }
}

