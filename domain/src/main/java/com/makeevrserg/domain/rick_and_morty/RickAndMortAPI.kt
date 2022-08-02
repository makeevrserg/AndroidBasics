package com.makeevrserg.domain.rick_and_morty

import com.makeevrserg.domain.rick_and_morty.models.Character
import com.makeevrserg.domain.rick_and_morty.models.PageModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortAPI {
    @GET("character")
    fun fetchCharacters(@Query("page") page:Int): Call<PageModel>
    @GET("character/{id}")
    fun fetchCharacter(@Path("id") id:String): Call<Character>
}