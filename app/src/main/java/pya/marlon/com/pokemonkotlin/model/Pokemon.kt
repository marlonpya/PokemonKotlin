package pya.marlon.com.pokemonkotlin.model

/**
 * Created by marlonpya on 24/06/17.
 */
data class Pokemon(
        val name: String,
        val url: String
) {
    val id: Int
        get() {
            val par = url.split(Regex.fromLiteral("/"))
            return (par[par.size - 2]).toInt()
        }
}
