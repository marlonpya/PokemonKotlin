package pya.marlon.com.pokemonkotlin.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pya.marlon.com.pokemonkotlin.api.response.ListPokemonResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * Created by marlonpya on 24/06/17.
 */

object RetrofitSession{
    val service: RetrofitService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
        service = retrofit.create(RetrofitService::class.java)
    }

    fun getPokemons(limit: Int, offSet: Int) : Observable<ListPokemonResponse>{
        return service.getPokemons(limit, offSet)
    }
}