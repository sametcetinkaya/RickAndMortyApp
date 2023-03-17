package com.sametcetinkaya.rickandmortyapp.data.api

import com.sametcetinkaya.rickandmortyapp.data.models.character.CharacterData
import com.sametcetinkaya.rickandmortyapp.data.models.character.CharacterResponse
import com.sametcetinkaya.rickandmortyapp.data.models.location.LocationsResponse
import com.sametcetinkaya.rickandmortyapp.data.models.location.SingleLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharacters(@Query("page") query: Int): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: String): CharacterData

    @GET
    suspend fun getManyCharacters(
        @Url url: String
    ): List<CharacterData>


    @GET("location/{id}")
    suspend fun getSingleLocation(@Path("id") id: Int): SingleLocation

    @GET("location")
    suspend fun getLocations(@Query("page") page: String): LocationsResponse
}