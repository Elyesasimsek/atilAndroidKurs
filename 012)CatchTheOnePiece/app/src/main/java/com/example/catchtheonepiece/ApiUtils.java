package com.example.catchtheonepiece;

public class ApiUtils {
    public static  final  String BASE_URL = "https://elyesasimsek.com/";
    public static  CharacterDAOInterface getFilmlerDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(CharacterDAOInterface.class);
    }
}
