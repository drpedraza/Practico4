package com.example.practicaapipersonas.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiacapipersonas.repositories.GenderRepository
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityMainBinding
import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.models.Generos
import com.example.practicaapipersonas.ui.adapters.GeneroAdapter
import com.example.practicaapipersonas.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), GeneroAdapter.OnCategoriaClickListener {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_go_next_page){
            val intent = Intent(this, GenderDetailActivity::class.java)
            startActivity(intent)
        }else if (item.itemId == R.id.action_genero){
            Toast.makeText(this, "Apretaste el boton generos", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaPersonas()
    }
    private fun setupEventListeners() {
        binding.fabAddCategoria.setOnClickListener {
            val intent = Intent(this, GenderDetailActivity::class.java)
            startActivity(intent)
        }
        binding.btnProductos.setOnClickListener {
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewModelListeners() {
        model.categoryList.observe(this) {
            val adapter = (binding.lstPersonas.adapter as GeneroAdapter)
            adapter.updateData(it)
        }
    }


    private fun setupRecyclerView() {
        binding.lstPersonas.apply {
            this.adapter = GeneroAdapter(Generos(), this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onCategoriaClick(categoria: Genero) {
        val intent = Intent(this, GenderDetailActivity::class.java)
        intent.putExtra("categoriaId", categoria.id)
        startActivity(intent)
    }

    override fun onCategoriaDelete(categoria: Genero) {
        GenderRepository.deleteCategory(categoria.id!!,
            success = {
                model.fetchListaPersonas()
            },
            failure = {
                it.printStackTrace()
            })
    }
}