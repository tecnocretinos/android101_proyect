package tech.yeswecode.reporteciudadano.utilities.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import tech.yeswecode.reporteciudadano.utilities.NotificationUtil

class FCMService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val data = message.data.toString()
        NotificationUtil.showNotification(this,"Reporte ciudadano", data)
    }
}