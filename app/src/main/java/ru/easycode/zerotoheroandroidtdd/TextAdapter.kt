package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextViewBinding

class TextAdapter : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    private val data = ArrayList<String>()

    fun addItem(text:String) {
        data.add(text)
        notifyItemInserted(data.size)
    }

    fun getItems() : ArrayList<String> {
        return data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTextViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(binding: ItemTextViewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val textView = binding.elementTextView

        fun bind(text: String) {
            textView.text = text
        }
    }
}