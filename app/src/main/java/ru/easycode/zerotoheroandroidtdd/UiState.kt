package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import java.io.Serializable

interface UiState : Serializable {
    fun apply(textView: TextView, progressBar: ProgressBar, actionButton: Button)

    object ShowProgress : UiState {
        override fun apply(
            textView: TextView,
            progressBar: ProgressBar,
            actionButton: Button
        ) {
            textView.isVisible = false
            progressBar.isVisible = true
            actionButton.isEnabled = false
        }
    }

    object ShowData : UiState {
        override fun apply(
            textView: TextView,
            progressBar: ProgressBar,
            actionButton: Button
        ) {
            textView.isVisible = true
            progressBar.isVisible = false
            actionButton.isEnabled = true
        }
    }
}