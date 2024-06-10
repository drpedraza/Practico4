package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.GenderRepository
import com.example.practicaapipersonas.models.Generos

class MainViewModel : ViewModel() {
    private val _categoryList: MutableLiveData<Generos> by lazy {
        MutableLiveData<Generos>(Generos())
    }
    val categoryList: LiveData<Generos> get() = _categoryList


    fun fetchListaPersonas() {
        GenderRepository.getGenderList(
            success = { personas ->
                personas?.let {

                    _categoryList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            })

    }
}