package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapipersonas.databinding.CategoriaItemLayoutBinding
import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.models.Generos

class GeneroAdapter(val personaList: Generos, val listener: OnCategoriaClickListener) :
    RecyclerView.Adapter<GeneroAdapter.PersonaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding =
            CategoriaItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PersonaViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personaList[position]
        holder.bind(persona, listener)
    }

    fun updateData(personaList: Generos) {
        this.personaList.clear()
        this.personaList.addAll(personaList)
        notifyDataSetChanged()
    }

    class PersonaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoria: Genero, listener: OnCategoriaClickListener) {
            val binding = CategoriaItemLayoutBinding.bind(itemView)
            binding.apply {
                lblCategoryName.text = categoria.nombre
                btnDeleteCategory.setOnClickListener {
                    listener.onCategoriaDelete(categoria)
                }
                lblCategoryName.setOnClickListener {
                    listener.onCategoriaClick(categoria)
                }
                root.setOnClickListener { listener.onCategoriaClick(categoria) }
            }

        }
    }

    interface OnCategoriaClickListener {
        fun onCategoriaClick(categoria: Genero)
        fun onCategoriaDelete(categoria: Genero)
    }
}