package com.example.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ItemPokemonBinding
import org.json.JSONObject


class MainAdapter(private val pokemons: ArrayList<JSONObject>): RecyclerView.Adapter<MainAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainHolder, position: Int) {
        holder.render(pokemons[position])
    }

    override fun getItemCount(): Int = pokemons.size

    class MainHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){

        fun render(pokemon: JSONObject){
            var num = pokemon.getString("url")
            binding.tvPokeListed.setText(num.substring(num.length - 2, num.length - 1))
            binding.tvPokeName.setText(pokemon.getString("name"))

        }
    }



}