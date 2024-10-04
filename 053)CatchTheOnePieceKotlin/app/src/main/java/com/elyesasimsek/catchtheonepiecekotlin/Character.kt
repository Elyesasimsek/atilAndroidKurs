package com.elyesasimsek.catchtheonepiecekotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character (
    @SerializedName("characterId")
    @Expose
    val characterId: String? = null,

    @SerializedName("characterName")
    @Expose
    val characterName: String? = null,

    @SerializedName("characterImage")
    @Expose
    val characterImage: String? = null
):Serializable