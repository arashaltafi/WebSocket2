package com.arash.altafi.websocket2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.websocket2.databinding.MessageListItemBinding
import com.arash.altafi.websocket2.model.MessageModel
import org.json.JSONException

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private val messagesList: ArrayList<MessageModel> = ArrayList()

    fun addItem(messageModel: MessageModel) {
        messagesList.add(messageModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MessageListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = messagesList[position]
        try {
            holder.bind(item)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = messagesList.size

    inner class ViewHolder(private val binding: MessageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MessageModel) = binding.apply {
            if (item.byServer) {
                receivedMessage.visibility = View.VISIBLE
                receivedMessage.text = item.message
                sentMessage.visibility = View.INVISIBLE
            } else {
                sentMessage.visibility = View.VISIBLE
                sentMessage.text = item.message
                receivedMessage.visibility = View.INVISIBLE
            }
        }
    }
}