package pya.marlon.com.pokemonkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import pya.marlon.com.pokemonkotlin.adapter.PokemonAdapter
import pya.marlon.com.pokemonkotlin.api.RetrofitSession
import pya.marlon.com.pokemonkotlin.api.response.ListPokemonResponse
import pya.marlon.com.pokemonkotlin.model.Pokemon
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var adapter: PokemonAdapter? = null
    private var offset = 0
    private var isLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PokemonAdapter(this)
        val layoutManager = GridLayoutManager(this, 3)
        rvPokemon.layoutManager = layoutManager
        rvPokemon.setHasFixedSize(true)
        rvPokemon.adapter = adapter
        rvPokemon.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 ) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItem = layoutManager.findFirstVisibleItemPosition()
                    if (isLoad) {
                        if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                            Log.d("MainActivity", "end...")
                            offset += 20
                            load()
                        }
                    }
                }
            }
        })
        load()
    }

    fun load() {
        enable(false)
        RetrofitSession().getPokemons(20, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    next -> adapter!!.load(map(next))
                }, {
                    error ->
                    run {
                        enable(true)
                        Log.d(TAG, error.toString())
                    }
                }, {
                    enable(true)
                   Log.d(TAG, "end")
                })
    }

    fun map(listPokemonResponse: ListPokemonResponse): ArrayList<Pokemon> {
        val pokems = ArrayList<Pokemon>()
        Log.d(TAG, "${listPokemonResponse.list.size}")
        for (i in listPokemonResponse.list.indices) {
            val list = listPokemonResponse.list
            pokems.add(Pokemon(list[i].name, list[i].url))
        }
        return pokems
    }

    fun enable(status: Boolean) {
        val visible = if (status) View.GONE else View.VISIBLE
        pbPokemon.visibility = visible
        isLoad = status
    }
}
