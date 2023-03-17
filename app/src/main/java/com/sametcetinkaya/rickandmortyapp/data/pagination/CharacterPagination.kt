package com.sametcetinkaya.rickandmortyapp.data.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sametcetinkaya.rickandmortyapp.data.api.RickAndMortyService
import com.sametcetinkaya.rickandmortyapp.data.models.character.CharacterData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterPagination @Inject constructor(private val rickAndMortyService : RickAndMortyService) {
    fun getCharacter() : Flow<PagingData<CharacterData>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 30),
            pagingSourceFactory = {
                CharacterPagingSource(rickAndMortyService)
            }
        ).flow
    }
}