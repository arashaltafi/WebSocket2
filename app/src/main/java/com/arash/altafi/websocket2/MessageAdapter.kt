package com.arash.altafi.websocket2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import org.json.JSONException
import org.json.JSONObject

class MessageAdapter: RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private val messagesList: MutableList<JSONObject> = ArrayList()

    fun addItem(item: JSONObject) {
        messagesList.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.message_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = messagesList[position]
        try {
            if (item.getBoolean("byServer")) {
                holder.receivedMessage.visibility = View.VISIBLE
                holder.receivedMessage.text = item.getString("message")
                holder.sentMessage.visibility = View.INVISIBLE
            } else {
                holder.sentMessage.visibility = View.VISIBLE
                holder.sentMessage.text = item.getString("message")
                holder.receivedMessage.visibility = View.INVISIBLE
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sentMessage: MaterialTextView
        var receivedMessage: MaterialTextView

        init {
            sentMessage = itemView.findViewById(R.id.sentMessage)
            receivedMessage = itemView.findViewById(R.id.receivedMessage)
        }
    }
}