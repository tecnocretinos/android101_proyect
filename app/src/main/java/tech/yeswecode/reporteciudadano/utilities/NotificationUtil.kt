package tech.yeswecode.reporteciudadano.utilities

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import tech.yeswecode.reporteciudadano.R

object NotificationUtil {

    private val CHANNEL_ID = "messages"
    private val CHANNEL_NAME = "Messages"
    private var id = 0

    fun showNotification(context: Context, title: String, message: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setDefaults(Notification.DEFAULT_ALL)
            .setContentText(message)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
        val notification = builder.build()
        notificationManager.notify(id, notification)
        id++
    }
}