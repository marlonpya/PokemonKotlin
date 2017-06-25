package pya.marlon.com.pokemonkotlin.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by marlonpya on 24/06/17.
 */
fun ImageView.load(url: String){
    Glide.with(context)
            .load(url)
            .centerCrop()
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
}