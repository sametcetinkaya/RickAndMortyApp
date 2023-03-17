package com.sametcetinkaya.rickandmortyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sametcetinkaya.rickandmortyapp.data.models.character.CharacterData
import com.sametcetinkaya.rickandmortyapp.data.pagination.CharacterPagination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterPagination: CharacterPagination ) :ViewModel() {

    fun getCharacterListData() : Flow<PagingData<CharacterData>> {
        return characterPagination.getCharacter().cachedIn(viewModelScope)
    }
}