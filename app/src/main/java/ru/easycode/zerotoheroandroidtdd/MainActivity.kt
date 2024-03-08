package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionButton.setOnClickListener {
            val textView = createTextView(binding.inputEditText.text.toString())
            binding.contentLayout.addView(textView)
            binding.inputEditText.setText("")
        }
    }

    private fun createTextView(text: String): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.id = View.generateViewId()
        return textView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val data = binding.contentLayout.children.map { it as? TextView }.filterNotNull()
            .map { it.text.toString() }.toList().toTypedArray()
        outState.putStringArray("key", data)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getStringArray("key")
        data?.forEach {
            val textView = createTextView(it)
            binding.contentLayout.addView(textView)
        }
    }
}