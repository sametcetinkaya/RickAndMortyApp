package com.sametcetinkaya.rickandmortyapp.data.models.character

import java.lang.Character

data class CharacterResponse (
    val info: Info,
    val results:List<CharacterData>
        )