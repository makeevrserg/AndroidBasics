package com.makeevrserg.feature_services.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class SimpleBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != Intent.ACTION_BOOT_COMPLETED) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            context?.startForegroundService(Intent(context,SampleService::class.java))

    }
}