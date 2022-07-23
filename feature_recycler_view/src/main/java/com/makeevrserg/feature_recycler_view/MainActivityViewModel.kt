package com.makeevrserg.feature_recycler_view

import BaseViewModel
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.makeevrserg.domain.rick_and_morty.RickAndMortApiBuilder
import com.makeevrserg.domain.rick_and_morty.RickAndMortyDataSource
import com.makeevrserg.domain.rick_and_morty.models.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainActivityViewModel(application: Application) : BaseViewModel(application) {
    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>>
        get() = _characters

    init {
        viewModelScope.launch {
            _characters.value = rickAndMortyDataSource.fetchCharacters()?.results ?: emptyList()
        }

    }
}
