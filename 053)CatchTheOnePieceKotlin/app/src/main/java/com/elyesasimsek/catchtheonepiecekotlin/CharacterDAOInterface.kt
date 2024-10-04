package com.elyesasimsek.catchtheonepiecekotlin

import android.telecom.Call
import retrofit2.http.GET

interface CharacterDAOInterface {
    @GET("onepiece/characters.php")
    fun allCharacters(): retrofit2.Call<OnePieceCharacterReply>
}