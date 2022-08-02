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
import com.makeevrserg.feature_recycler_view.adapter.header_adapter.AdapterItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainActivityViewModel(application: Application) : BaseViewModel(application) {
    fun loadNextPage() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            println("Loading page $page")
            val list = rickAndMortyDataSource.fetchCharacters(page++)?.results
            if (list.isNullOrEmpty()) {
                page--
                isLoading = false
                return@launch
            }
            _characters.value =
                characters.value.toMutableList().apply { addAll(list) }.distinctBy { it.id }
            isLoading = false

        }
    }

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>>
        get() = _characters
    private var page = 0
    private var isLoading = false

    init {
        loadNextPage()
    }
}

val List<Character>.asHeader: List<AdapterItem>
    get() {
        val grouped = this.distinctBy { it.id }.groupBy { it.species }
        var i = 0
        return grouped.flatMap { (spec, list) ->
            mutableListOf<AdapterItem>().apply {
                add(AdapterItem.Header(spec))
                addAll(
                    list.map {
                        AdapterItem.CharacterItem(i++, it)
                    }
                )
            }
        }
    }