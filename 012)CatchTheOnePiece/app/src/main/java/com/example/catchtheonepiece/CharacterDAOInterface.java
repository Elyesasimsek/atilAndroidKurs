package com.example.catchtheonepiece;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterDAOInterface {
    @GET("onepiece/characters.php")
    Call<OnePieceCharacterReply> allCharacters();
}
