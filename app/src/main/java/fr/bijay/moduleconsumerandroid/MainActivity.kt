package fr.bijay.moduleconsumerandroid

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import fr.bijay.moduleconsumerandroid.databinding.ActivityMainBinding
import fr.bijay.moduleconsumerandroid.domain.utils.DataState

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        binding.dataButton.setOnClickListener {
            viewModel.fetchDatas()
        }
    }

    private fun setupObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<String?> -> {
                    toggleLoader(false)
                    if (dataState.data != null) {
                        binding.tvDummy.text = dataState.data
                    } else {
                        showToastError(null)
                    }
                }
                is DataState.Loading -> {
                    toggleLoader(true)
                }
                is DataState.Error -> {
                    toggleLoader(false)
                    showToastError(dataState.exception.message)
                }
            }
        })
    }

    private fun toggleLoader(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun showToastError(message: String?) {
        if (message.isNullOrEmpty()) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }
}