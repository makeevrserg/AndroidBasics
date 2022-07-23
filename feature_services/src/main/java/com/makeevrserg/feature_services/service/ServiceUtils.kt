package com.makeevrserg.feature_services.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.makeevrserg.feature_services.FeatureServicesMainActivity
import com.makeevrserg.feature_services.R

object ServiceUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun Service.sendNotification(channelID: String, msg:String) {
        createNotificationChannel(channelID,"name")
        val notificationID = 101

        val resultIntent = Intent(this, FeatureServicesMainActivity::class.java)
        val resultPendingIntent = PendingIntent.getActivity(
            this, 0, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Android Essentials")
            .setContentText("Service is working: $msg")
            .setContentIntent(resultPendingIntent)
            .setSmallIcon(R.drawable.bobby_moynihan)
            .setAutoCancel(true)
            .build()
        startForeground(notificationID, notification)

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun Context.createNotificationChannel(channelId: String, channelName: String): String{
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}