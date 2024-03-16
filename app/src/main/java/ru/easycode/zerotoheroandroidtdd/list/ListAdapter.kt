package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemElementBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val data = ArrayList<CharSequence>()

    fun update(it: List<CharSequence>) {
        val diffUtilCallback = DiffUtilCallback(data, it)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        data.clear()
        data.addAll(it)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun saveInstanceState(outState: Bundle) {
        outState.putCharSequenceArrayList("key", data)
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        data.addAll(savedInstanceState.getStringArrayList("key") ?: arrayListOf())
        notifyItemRangeInserted(0, data.size)
    }

    class ViewHolder(binding: ItemElementBinding) : RecyclerView.ViewHolder(binding.root) {
        private val textView = binding.elementTextView

        fun bind(text: CharSequence) {
            textView.text = text
        }
    }

    private class DiffUtilCallback(
        private val old: List<CharSequence>,
        private val new: List<CharSequence>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return old.size
        }

        override fun getNewListSize(): Int {
            return new.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }

        override fun getChangePayload(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Pair<CharSequence, CharSequence> {
            return Pair(old[oldItemPosition], new[newItemPosition])
        }
    }
}