package com.makeevrserg.feature_intents

import AndroidExtensions.launchActivity
import BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.makeevrserg.feature_intents.databinding.FeatureIntentsActivityMainBinding
import java.util.*

class FeatureIntentsMainActivity :
    BaseActivity<FeatureIntentsActivityMainBinding>(FeatureIntentsActivityMainBinding::inflate) {
    private var extraTitle: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let {
            extraTitle = it.getStringExtra(TITLE_EXTRA)
            binding?.let(::setupObservables)
        }
    }

    override fun setupObservables(binding: FeatureIntentsActivityMainBinding) {
        binding.bLaunchActivity.setOnClickListener {
            launchActivity<FeatureIntentsMainActivity> {
                this.putExtra(TITLE_EXTRA, UUID.randomUUID().toString())
            }
        }
        extraTitle?.let {
            binding.bLaunchActivity.visibility = View.GONE
            binding.tvTitle.text = getString(R.string.feature_intents_launched)
            binding.tvTitleExtra.text = it
        }
    }

    companion object {
        private const val TITLE_EXTRA = "TITLE_EXTRA"
    }
}