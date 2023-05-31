package com.example.onlinenote.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.databinding.ItemNoteBinding

class NoteAdapter : ListAdapter<NetworkNote, NoteAdapter.ViewHolder>(myDiffUtil) {

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val note = getItem(position)
            binding.tvTitle.text = note.title
            binding.tvText.text = setText(note)
            binding.tvDate.text = note.lastUpdatedDate

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(note)
            }
        }
    }

    private var onItemClickListener: ((NetworkNote) -> Unit)? = null
    fun setOnItemClickListener(block: (NetworkNote) -> Unit) {
        onItemClickListener = block
    }

    object myDiffUtil : DiffUtil.ItemCallback<NetworkNote>() {
        override fun areItemsTheSame(oldItem: NetworkNote, newItem: NetworkNote) = oldItem == newItem

        override fun areContentsTheSame(oldItem: NetworkNote, newItem: NetworkNote) =
                    oldItem.title == newItem.title
                    && oldItem.text == newItem.text
                    && oldItem.lastUpdatedDate == newItem.lastUpdatedDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setText(note: NetworkNote): String {
        val text = note.text.toString()

        return if (text.length< 10) {
            text
        } else {
            var _text = ""
            repeat(10) {
                _text += text[it]
            }
            _text
        }
    }
}