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

class CharacterAdapter : PagingDataAdapter<CharacterData, CharacterAdapter.CharacterViewHolder>(DiffUtilCallBack()) {

    var onCharacterItemClick :(CharacterData) -> Unit = {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterRowBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(characterViewHolder: CharacterViewHolder, position: Int) {
        characterViewHolder.bind(getItem(position)!!)

        characterViewHolder.itemView.setOnClickListener {
            onCharacterItemClick.invoke(getItem(position)!!)
        }

    }

    class CharacterViewHolder(private val binding: CharacterRowBinding) :
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