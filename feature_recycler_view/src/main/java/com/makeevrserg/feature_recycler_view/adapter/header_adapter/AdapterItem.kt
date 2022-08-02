package com.makeevrserg.feature_recycler_view.adapter.header_adapter

import com.makeevrserg.domain.rick_and_morty.models.Character

sealed class AdapterItem() {
    abstract val id: Int

    data class CharacterItem(val index:Int,val character: Character) : AdapterItem() {
        override val id: Int
            get() = character.id
    }

    data class Header(val text: String) : AdapterItem() {
        override val id: Int
            get() = Int.MIN_VALUE
    }
}