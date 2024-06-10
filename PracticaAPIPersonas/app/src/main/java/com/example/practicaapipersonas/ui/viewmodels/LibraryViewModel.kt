package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.LibraryRepository
import com.example.practicaapipersonas.models.Libro

class LibraryViewModel : ViewModel() {
    private val _productList = MutableLiveData<List<Libro>>()
    val productList: LiveData<List<Libro>> get() = _productList

    fun fetchProducts() {
        LibraryRepository.getLibrars(
            success = { _productList.value = it },
            failure = { it.printStackTrace() })
    }
}

