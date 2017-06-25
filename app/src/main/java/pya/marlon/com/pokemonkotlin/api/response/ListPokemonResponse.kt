package pya.marlon.com.pokemonkotlin.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by marlonpya on 24/06/17.
 */
data class ListPokemonResponse(
        @SerializedName("results")
        val list: ArrayList<PokemonResponse>
)