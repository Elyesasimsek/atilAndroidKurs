package com.elyesasimsek.catchtheonepiecekotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OnePieceCharacterReply (
    @SerializedName("characters")
    @Expose
    val characters: List<Character>? = null,

    @SerializedName("success")
    @Expose
    val success: Int? = null
)