package ru.easycode.zerotoheroandroidtdd

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import java.io.Serializable

interface UiState : Serializable {

    fun apply(textView: TextView, progressBar: ProgressBar, button: Button)

    object Initial : UiState {
        override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
            textView.isInvisible = true
            progressBar.isInvisible = true
            button.isEnabled = true
        }
    }

    object ShowProgress : UiState {
        override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
            textView.isInvisible = true
            progressBar.isInvisible = false
            button.isEnabled = false
        }
    }

    data class ShowData(private val text: String) : UiState {
        override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
            textView.text = text
            textView.isInvisible = false
            progressBar.isInvisible = true
            button.isEnabled = true
        }
    }
}
