package tech.yeswecode.reporteciudadano.utilities.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import tech.yeswecode.reporteciudadano.utilities.NotificationUtil

data class FCMNotification(val id: String = "",
                           val title: String = "",
                           val message: String = "")

class FCMService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val data = message.data
        val json = Gson().toJson(data)
        val notification = Gson().fromJson<FCMNotification>(json, FCMNotification::class.java)
        NotificationUtil.showNotification(this,
            notification.title,
            notification.message)
    }
}