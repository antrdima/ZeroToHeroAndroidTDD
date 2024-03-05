package ru.easycode.zerotoheroandroidtdd.ui

import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import java.io.Serializable

interface UiState : Serializable {

    fun apply(textView: TextView, progressBar: ProgressBar, button: Button)

//    object Initial : UiState {
//        override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
//            textView.isInvisible = true
//            progressBar.isInvisible = true
//            button.isEnabled = true
//        }
//    }

    object ShowProgress : UiState {
        override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
            textView.isVisible = false
            progressBar.isVisible = true
            button.isEnabled = false
        }
    }

    data class ShowData(private val text: String) : UiState {
        override fun apply(textView: TextView, progressBar: ProgressBar, button: Button) {
            textView.text = text
            textView.isVisible = true
            progressBar.isVisible = false
            button.isEnabled = true
        }
    }
}
