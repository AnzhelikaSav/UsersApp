package com.example.usersapp.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.domain.models.User
import com.example.usersapp.databinding.ItemUserBinding
import java.util.UUID

class UserAdapter(private val onItemClick: (UUID) -> Unit) : RecyclerView.Adapter<UserAdapter.Holder>() {

    var items: List<User> = emptyList()
        set(newList) {
            val diffUtil = DiffCallback(field, newList)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
            field = newList
            diffUtilResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            binding = ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    inner class Holder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvName.text = user.name
                Glide.with(binding.root)
                    .load(user.imageUrl)
                    .circleCrop()
                    .into(binding.ivAvatar)
                root.setOnClickListener { onItemClick.invoke(user.uuid) }
            }
        }
    }

    inner class DiffCallback(
        private val old: List<User>,
        private val new: List<User>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return old.size
        }

        override fun getNewListSize(): Int {
            return new.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition].uuid == new[newItemPosition].uuid
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return old[oldItemPosition] == new[newItemPosition]
        }

    }
}