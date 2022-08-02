package com.makeevrserg.domain.rick_and_morty

import com.makeevrserg.domain.rick_and_morty.models.Character
import com.makeevrserg.domain.rick_and_morty.models.PageModel
import retrofit2.Call
import retrofit2.await
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.Exception

class RickAndMortyDataSource(private val api: RickAndMortAPI) {

    suspend fun fetchCharacters(page:Int) = catching(true) { api.fetchCharacters(page)?.await() }
    suspend fun fetchCharacter(id: String) = catching(true) { api.fetchCharacter(id)?.await() }
}

suspend fun <T> catching(stackTrace: Boolean = false, block: suspend () -> T): T? = try {
    block()
} catch (e: Exception) {
    if (stackTrace)
        e.printStackTrace()
    null
}