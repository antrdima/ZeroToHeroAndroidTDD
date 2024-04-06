package ru.easycode.zerotoheroandroidtdd.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.data.ItemUi
import ru.easycode.zerotoheroandroidtdd.databinding.ItemElementBinding

class ListAdapter(private val clickListener: MainActivity.ClickListener) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val data = ArrayList<ItemUi>()

    fun update(it: List<ItemUi>) {
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
        holder.bind(data[position], clickListener)
    }

//    fun saveInstanceState(outState: Bundle) {
//        outState.putCharSequenceArrayList("key", data)
//    }
//
//    fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        data.addAll(savedInstanceState.getStringArrayList("key") ?: arrayListOf())
//        notifyItemRangeInserted(0, data.size)
//    }

    class ViewHolder(private val binding: ItemElementBinding) : RecyclerView.ViewHolder(binding.root) {
        private val textView = binding.elementTextView

        fun bind(itemUi: ItemUi, clickListener: MainActivity.ClickListener) {
            textView.text = itemUi.text
            binding.root.setOnClickListener {
                clickListener.onClick(itemUi.id)
            }
        }
    }

    private class DiffUtilCallback(
        private val old: List<ItemUi>,
        private val new: List<ItemUi>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return old.size
        }

        override fun getNewListSize(): Int {
            return new.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].id == new[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].text == new[newItemPosition].text
        }

        override fun getChangePayload(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Pair<ItemUi, ItemUi> {
            return Pair(old[oldItemPosition], new[newItemPosition])
        }
    }
}