package com.elyesasimsek.catchtheonepiecekotlin

class ApiUtils {
    companion object{
        const val BASE_URL = "https://elyesasimsek.com/"

        fun getCharacterDAOInterface(): CharacterDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(CharacterDAOInterface::class.java)
        }
    }
}