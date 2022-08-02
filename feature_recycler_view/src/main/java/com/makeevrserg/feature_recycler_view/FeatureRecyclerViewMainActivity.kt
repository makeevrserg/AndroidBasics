package com.makeevrserg.feature_recycler_view

import BaseActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.makeevrserg.feature_recycler_view.adapter.header_adapter.EasyAdapterImpl
import com.makeevrserg.feature_recycler_view.adapter.header_adapter.HeaderAdapter
import com.makeevrserg.feature_recycler_view.databinding.ActivityMainRecyclerViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import su.crm.glavcontact.utils.addOnEndReachedListener


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

        val adapter = EasyAdapterImpl()
        binding.rv.adapter = adapter
        binding.rv.addOnEndReachedListener {
            viewModel.loadNextPage()
        }
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.characters.collectLatest {
                adapter.submitList(it.asHeader)
//                adapter.submitList(it)
            }

        }
    }
}
