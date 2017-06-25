package pya.marlon.com.pokemonkotlin.api.response

/**
 * Created by marlonpya on 24/06/17.
 */
data class PokemonResponse(
        val name: String,
        val url: String
){
    override fun toString(): String {
        return "PokemonResponse(name='$name', url='$url')"
    }
}