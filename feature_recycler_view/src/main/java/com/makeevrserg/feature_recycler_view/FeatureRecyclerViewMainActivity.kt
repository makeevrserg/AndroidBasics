package com.makeevrserg.feature_recycler_view

import BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.makeevrserg.feature_recycler_view.adapter.SimpleAdapter
import com.makeevrserg.feature_recycler_view.databinding.ActivityMainRecyclerViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class FeatureRecyclerViewMainActivity() :
    BaseActivity<ActivityMainRecyclerViewBinding>(ActivityMainRecyclerViewBinding::inflate) {
    private val viewModel by lazy {
        defaultViewModelProviderFactory.create(MainActivityViewModel::class.java)
    }

    override val toolBarTitle: String = "FeatureRecyclerViewMainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
    }

    override fun setupObservables(binding: ActivityMainRecyclerViewBinding) {

        val adapter = SimpleAdapter()
        binding.rv.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.characters.collectLatest {
                adapter.submitList(it)
            }

        }
    }
}
