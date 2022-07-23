package com.makeevrserg.feature_services.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.makeevrserg.feature_services.FeatureServicesMainActivity
import com.makeevrserg.feature_services.R
import com.makeevrserg.feature_services.service.ServiceUtils.sendNotification
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class SampleService : Service() {
    companion object{
        const val TAG = "SampleService"
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        GlobalScope.launch {
            while(true){
                delay(200)
                Log.d(TAG, "onStartCommand: ")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    sendNotification("SampleID", Random.nextInt(100).toString())
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}

