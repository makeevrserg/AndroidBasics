package com.makeevrserg.androidessentials

import AndroidExtensions.launchActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.makeevrserg.androidessentials.databinding.ActivityMainBinding
import com.makeevrserg.feature_intents.FeatureIntentsMainActivity

abstract class BaseActivity<T : ViewBinding>(private val bindingFactory: (LayoutInflater) -> T) :
    AppCompatActivity() {
    var binding: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        binding?.root?.let(::setContentView)
        binding?.let(::setupObservables)
    }

    abstract fun setupObservables(binding: T)

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}



class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun setupObservables(binding: ActivityMainBinding) {
        binding.buttonLaunchFeaturesIntents.setOnClickListener {
            launchActivity<FeatureIntentsMainActivity>()
        }

    }
}