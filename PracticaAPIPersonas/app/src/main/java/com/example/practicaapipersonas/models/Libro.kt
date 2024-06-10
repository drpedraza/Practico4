package com.example.practicaapipersonas.models

data class Libro (
        val id: Int,
        val nombre: String,
        val autor: String,
        val editorial: String,
        val imagen: String,
        val isbn: String,
        val sipnosis: String,
        val calificacion: Int,
        val createdAt: String,
        val updatedAt: String
    )
