package ru.easycode.zerotoheroandroidtdd.folder.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemFolderBinding
import ru.easycode.zerotoheroandroidtdd.folder.core.data.FolderUi

class FoldersAdapter(private val clickListener: FolderClickListener) :
    RecyclerView.Adapter<FoldersAdapter.ViewHolder>() {

    private val data = ArrayList<FolderUi>()

    fun update(it: List<FolderUi>) {
        val diffUtilCallback = DiffUtilCallback(data, it)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        data.clear()
        data.addAll(it)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class ViewHolder(private val binding: ItemFolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.folderTitleTextView
        private val count = binding.folderCountTextView

        fun bind(folderUi: FolderUi, clickListener: FolderClickListener) {
            binding.folderTitleTextView.text = folderUi.title
            binding.folderCountTextView.text = folderUi.notesCount.toString()
            binding.root.setOnClickListener {
                clickListener.onClick(folderUi)
            }
        }
    }

    private class DiffUtilCallback(
        private val old: List<FolderUi>,
        private val new: List<FolderUi>
    ) : DiffUtil.Callback() {
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
            return old[oldItemPosition].title == new[newItemPosition].title
                    && old[oldItemPosition].notesCount == new[newItemPosition].notesCount
        }

        override fun getChangePayload(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Pair<FolderUi, FolderUi> {
            return Pair(old[oldItemPosition], new[newItemPosition])
        }
    }
}