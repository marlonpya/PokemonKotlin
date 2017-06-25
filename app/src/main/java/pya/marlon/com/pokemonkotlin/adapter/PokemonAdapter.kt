package pya.marlon.com.pokemonkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_pokemon.view.*
import pya.marlon.com.pokemonkotlin.R
import pya.marlon.com.pokemonkotlin.extension.load
import pya.marlon.com.pokemonkotlin.model.Pokemon

/**
 * Created by marlonpya on 24/06/17.
 */
class PokemonAdapter(context: Context): RecyclerView.Adapter<PokemonAdapter.ViewHolder>(){
    var inflater : LayoutInflater? = null
    var pokemons: ArrayList<Pokemon>? = null

    init{
        this.inflater = LayoutInflater.from(context)
        this.pokemons = ArrayList<Pokemon>()
    }

    public fun load(pokemons: ArrayList<Pokemon>){
        this.pokemons!!.addAll(pokemons)
        notifyDataSetChanged()
    }

    fun ad(pokemon: Pokemon) {
        this.pokemons!!.add(pokemon)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return pokemons!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int): ViewHolder {
        return ViewHolder(inflater!!.inflate(R.layout.row_pokemon, parent, false))
    }

    override fun onBindViewHolder(parent: ViewHolder?, position: Int) {
        val pokemon = pokemons!!.get(position)
        parent!!.bind(pokemon)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(pokemon: Pokemon) {
            with(pokemon){
                itemView.lblName.text = pokemon.name
                itemView.ivPokemon.load("http://pokeapi.co/media/sprites/pokemon/" + pokemon.id + ".png")
            }
        }
    }

}