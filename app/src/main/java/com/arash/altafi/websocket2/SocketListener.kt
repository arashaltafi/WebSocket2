package com.arash.altafi.websocket2

import android.app.Activity
import android.widget.Toast
import androidx.annotation.Nullable
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONException
import org.json.JSONObject

class SocketListener(private val activity: Activity, private val adapter: MessageAdapter): WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        activity.runOnUiThread {
            Toast.makeText(activity, "Connection Success!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        activity.runOnUiThread {
            val jsonObject = JSONObject()
            try {
                jsonObject.put("message", text)
                jsonObject.put("byServer", true)
                adapter.addItem(jsonObject)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, @Nullable response: Response?) {
        super.onFailure(webSocket, t, response)
    }

}