package com.arash.altafi.websocket2

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var webSocket: WebSocket? = null
    private var messageAdapter: MessageAdapter = MessageAdapter()
    private lateinit var messageList: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var send: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        init()
        instantiateWebSocket()
    }

    private fun bindViews() {
        messageList = findViewById(R.id.messageList)
        messageBox = findViewById(R.id.messageBox)
        send = findViewById(R.id.send)
    }

    private fun init() {
        messageList.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }

        send.setOnClickListener {
            val message = messageBox.text.toString()
            if (message.isNotEmpty()) {
                webSocket?.send(message)
                messageBox.setText("")
                val jsonObject = JSONObject()
                try {
                    jsonObject.put("message", message)
                    jsonObject.put("byServer", false)
                    messageAdapter.addItem(jsonObject)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun instantiateWebSocket() {
        val client = OkHttpClient()
        val request: Request = Request.Builder().url("ws://192.168.1.101:3000").build()
        val socketListener = SocketListener(this, messageAdapter)
        webSocket = client.newWebSocket(request, socketListener)
    }

}