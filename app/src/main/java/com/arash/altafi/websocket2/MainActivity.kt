package com.arash.altafi.websocket2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arash.altafi.websocket2.databinding.ActivityMainBinding
import com.arash.altafi.websocket2.model.MessageModel
import com.arash.altafi.websocket2.utils.WebSocketClient
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var webSocket: WebSocketClient? = null
    private var messageAdapter: MessageAdapter = MessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        setupWebSocket()
    }

    private fun init() = binding.apply {
        messageList.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        send.setOnClickListener {
            val message = messageBox.text.toString()
            if (message.isNotEmpty()) {
                webSocket?.send(message)
                messageBox.setText("")
                val messageModel = MessageModel(message, false)
                messageAdapter.addItem(messageModel)
            }
        }
    }

    private fun setupWebSocket() = binding.apply {
        webSocket = WebSocketClient(URL) { message ->
            runOnUiThread {
                try {
                    val messageModel = MessageModel(message, true)
                    messageAdapter.addItem(messageModel)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        webSocket?.connect()
    }

    private companion object {
        const val URL = "ws://192.168.1.101:8080"
    }

}