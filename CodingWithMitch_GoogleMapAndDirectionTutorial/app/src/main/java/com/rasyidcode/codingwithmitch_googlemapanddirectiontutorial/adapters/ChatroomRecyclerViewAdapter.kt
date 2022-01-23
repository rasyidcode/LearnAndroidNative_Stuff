package com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.databinding.ChatroomListItemBinding
import com.rasyidcode.codingwithmitch_googlemapanddirectiontutorial.models.Chatroom

class ChatroomRecyclerViewAdapter(
    private val chatrooms: ArrayList<Chatroom> = arrayListOf(),
    private val chatroomClickListener: ChatroomRecyclerViewItemClickListener
) : RecyclerView.Adapter<ChatroomRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ChatroomListItemBinding,
        private val clickListener: ChatroomRecyclerViewItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(chatroom: Chatroom) {
            binding.chatroomTitle.text = chatroom.title
        }

        override fun onClick(p0: View?) {
            clickListener.onChatroomSelected(adapterPosition)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatroomRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            ChatroomListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), chatroomClickListener
        )
    }

    override fun onBindViewHolder(holder: ChatroomRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(chatrooms[position])
    }

    override fun getItemCount(): Int {
        return chatrooms.size
    }

    interface ChatroomRecyclerViewItemClickListener {
        fun onChatroomSelected(position: Int)
    }
}