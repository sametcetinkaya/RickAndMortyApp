package com.sametcetinkaya.rickandmortyapp.data.models.location

import com.sametcetinkaya.rickandmortyapp.data.models.character.Info

data class LocationsResponse(
    val info: Info,
    val results:List<SingleLocation>
)
