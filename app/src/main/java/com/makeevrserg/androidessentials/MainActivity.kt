package com.makeevrserg.androidessentials

import AndroidExtensions.launchActivity
import BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.makeevrserg.androidessentials.databinding.ActivityMainBinding
import com.makeevrserg.feature_intents.FeatureIntentsMainActivity
import com.makeevrserg.feature_recycler_view.FeatureRecyclerViewMainActivity
import com.makeevrserg.feature_services.FeatureServicesMainActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override val toolBarTitle: String
        get() = getString(R.string.app_name)
    override fun setupObservables(binding: ActivityMainBinding) {
        binding.buttonLaunchFeaturesIntents.setOnClickListener {
            launchActivity<FeatureIntentsMainActivity>()
        }
        binding.buttonLaunchRecyclerView.setOnClickListener {
            launchActivity<FeatureRecyclerViewMainActivity>()
        }
        binding.buttonLaunchService.setOnClickListener {
            launchActivity<FeatureServicesMainActivity>()
        }

    }
}