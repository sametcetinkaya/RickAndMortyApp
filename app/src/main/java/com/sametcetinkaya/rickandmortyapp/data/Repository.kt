package com.sametcetinkaya.rickandmortyapp.data

import com.sametcetinkaya.rickandmortyapp.data.api.RickAndMortyService
import com.sametcetinkaya.rickandmortyapp.data.models.character.CharacterResponse
import javax.inject.Inject

class Repository @Inject constructor(private val rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacters(page: Int): CharacterResponse =
        rickAndMortyService.getCharacters(page)

    suspend fun getCharacter(id: String) = rickAndMortyService.getCharacter(id)
    suspend fun getSingleLocation(id: Int) = rickAndMortyService.getSingleLocation(id)

}