package com.sametcetinkaya.rickandmortyapp.ui.character_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sametcetinkaya.rickandmortyapp.data.models.character.CharacterData
import com.sametcetinkaya.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import com.sametcetinkaya.rickandmortyapp.utils.loadImage
import com.sametcetinkaya.rickandmortyapp.utils.observeApiResultGeneric
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private lateinit var binding : FragmentCharacterDetailBinding
    private val viewModel : CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterDetailBinding.inflate(inflater, container , false)
        return binding.apply { val characterData = args
            characterAvatarImage.loadImage(args.characterData.image)
            toolbarLayout.toolbarTitle.text = args.characterData.name
            status.text = args.characterData.status
            species.text = args.characterData.species
            gender.text = args.characterData.gender
            origin.text = args.characterData.origin.name
            location.text = args.characterData.location.name
            //episodes.text = args.characterData.episode.toString()

        }.root




    }

    private fun setDataToView(characterData : CharacterData) = with(binding){
        characterAvatarImage.loadImage(characterData.image)
    }



}