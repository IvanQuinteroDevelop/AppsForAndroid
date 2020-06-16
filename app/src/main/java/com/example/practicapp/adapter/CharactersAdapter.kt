package com.example.practicapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicapp.R
import com.example.practicapp.models.Result


class CharactersAdapter(private var characters: List<Result>, var  context: Context) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.nameCharacter)
        var specie: TextView = view.findViewById(R.id.especieCharacter)
        var image: ImageView = view.findViewById(R.id.imageCharacter)
        var cardView: CardView = view.findViewById(R.id.card_view)


        fun bind(characters: Result, context: Context){
            name.text = characters.name
            specie.text = characters.species
            Glide.with(context)
                .load(characters.image)
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.fallo_wifi)
                .into(image)
                val animation: Animation =
                    AnimationUtils
                        .loadAnimation(context, R.anim.animation_resource)
                cardView.startAnimation(animation)
                        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val layoutInflater =  LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
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