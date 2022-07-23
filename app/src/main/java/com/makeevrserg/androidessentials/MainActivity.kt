package com.makeevrserg.androidessentials

import AndroidExtensions.launchActivity
import BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.makeevrserg.androidessentials.databinding.ActivityMainBinding
import com.makeevrserg.feature_intents.FeatureIntentsMainActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun setupObservables(binding: ActivityMainBinding) {
        binding.buttonLaunchFeaturesIntents.setOnClickListener {
            launchActivity<FeatureIntentsMainActivity>()
        }

    }
}