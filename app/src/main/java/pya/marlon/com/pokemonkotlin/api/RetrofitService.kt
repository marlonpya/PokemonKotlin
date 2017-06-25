package pya.marlon.com.pokemonkotlin.api

import pya.marlon.com.pokemonkotlin.api.response.ListPokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by marlonpya on 24/06/17.
 */
interface RetrofitService {

    @GET("pokemon")
    fun getPokemons(
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
    ) : Observable<ListPokemonResponse>
}