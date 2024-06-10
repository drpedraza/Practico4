package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practiacapipersonas.repositories.GenderRepository
import com.example.practicaapipersonas.models.Genero

class GenderDetailViewModel : ViewModel() {
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    private val _category: MutableLiveData<Genero?> by lazy {
        MutableLiveData<Genero?>(null)
    }
    val category: LiveData<Genero?> get() = _category

    fun saveCategory(nombre: String, id: Int) {
        val category = Genero(
            nombre = nombre
        )
        if (id != 0) {
            category.id = id
            GenderRepository.updateGender(category,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        } else {
            GenderRepository.insertGender(category,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }

    fun loadCategory(id: Int) {
        GenderRepository.getCategory(id,
            success = {
                _category.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }
}