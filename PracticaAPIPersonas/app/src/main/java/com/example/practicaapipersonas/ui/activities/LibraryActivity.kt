package com.example.practicaapipersonas.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaapipersonas.databinding.ActivityLibraryBinding
import com.example.practicaapipersonas.ui.adapters.LibraryAdapter
import com.example.practicaapipersonas.ui.viewmodels.LibraryViewModel

class LibraryActivity : AppCompatActivity() {
    private val model: LibraryViewModel by viewModels()
    private lateinit var binding: ActivityLibraryBinding
    private lateinit var productAdapter: LibraryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        observeViewModel()

        model.fetchProducts()
    }

    private fun setupViews() {
        setupRecyclerView()
        }

    private fun setupRecyclerView() {
        productAdapter = LibraryAdapter()
        binding.lstProductos.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@LibraryActivity)
        }
    }

    private fun observeViewModel() {
        model.productList.observe(this) { products ->
            productAdapter.submitList(products)
        }
    }
}

