package com.example.practicapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.practicapp.R
import com.example.practicapp.models.Result

class CharactersAdapter(private var characters: List<Result>, var  context: Context) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.nameCharacter)
        var specie: TextView = view.findViewById(R.id.especieCharacter)
        var image: ImageView = view.findViewById(R.id.imageCharacter)

        fun bind(characters: Result, context: Context){
            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            name.text = characters.name
            specie.text = characters.species
            Glide.with(context)
                .load(characters.image)
                /*.apply {
                    RequestOptions().placeholder(R.drawable.default_image).transforms(
                        RoundedCorners(40), CenterCrop()
                    )
                }
                .transition(DrawableTransitionOptions.withCrossFade(factory))*/
                .into(image)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater =  LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return characters.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characters[position]
        holder.bind(item, this.context)
    }
}