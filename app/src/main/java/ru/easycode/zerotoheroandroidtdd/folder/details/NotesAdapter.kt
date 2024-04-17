package ru.easycode.zerotoheroandroidtdd.folder.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemNoteBinding
import ru.easycode.zerotoheroandroidtdd.note.core.data.NoteUi


class NotesAdapter(private val clickListener: NoteClickListener) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val data = ArrayList<NoteUi>()

    fun update(it: List<NoteUi>) {
        val diffUtilCallback = DiffUtilCallback(data, it)
        val diff = DiffUtil.calculateDiff(diffUtilCallback)
        data.clear()
        data.addAll(it)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(noteUi: NoteUi, clickListener: NoteClickListener) {
            binding.noteTitleTextView.text = noteUi.title
            binding.root.setOnClickListener {
                clickListener.onClick(noteUi)
            }
        }
    }

    private class DiffUtilCallback(
        private val old: List<NoteUi>,
        private val new: List<NoteUi>
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
            return old[oldItemPosition].title == new[newItemPosition].title
        }

        override fun getChangePayload(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Pair<NoteUi, NoteUi> {
            return Pair(old[oldItemPosition], new[newItemPosition])
        }
    }
}