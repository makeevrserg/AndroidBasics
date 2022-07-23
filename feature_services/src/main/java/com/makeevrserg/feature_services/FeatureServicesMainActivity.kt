package com.makeevrserg.feature_services

import BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.makeevrserg.feature_services.databinding.FeatureServicesActivityMainBinding
import com.makeevrserg.feature_services.service.SampleService

class FeatureServicesMainActivity() :
    BaseActivity<FeatureServicesActivityMainBinding>(FeatureServicesActivityMainBinding::inflate) {
    override val toolBarTitle: String = "FeatureServicesMainActivity"
    override fun setupObservables(binding: FeatureServicesActivityMainBinding) {
        Intent(this,SampleService::class.java).also(::startService)
    }
}