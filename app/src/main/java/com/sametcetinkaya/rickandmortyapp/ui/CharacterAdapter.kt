package com.sametcetinkaya.rickandmortyapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sametcetinkaya.rickandmortyapp.data.models.character.CharacterData
import com.sametcetinkaya.rickandmortyapp.databinding.CharacterRowBinding
import com.sametcetinkaya.rickandmortyapp.utils.getColorStatus

class CharacterAdapter : PagingDataAdapter<CharacterData, CharacterAdapter.MyViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CharacterRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class MyViewHolder(private val binding: CharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind (characterData : CharacterData){
                binding.characterName.text = characterData.name
                binding.characterSpecies.text = characterData.species
                binding.characterGender.text = characterData.gender

                Glide.with(binding.characterImage)
                    .load(characterData.image)
                    .circleCrop()
                    .into(binding.characterImage)
                binding.characterArea.setBackgroundColor(getColorStatus(characterData.gender, itemView.context))

            }
        }

    class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterData>(){
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem == newItem
        }
    }
}