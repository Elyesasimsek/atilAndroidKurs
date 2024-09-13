package com.example.a021retrofitjava.service;

public class ApiUtils {
    public static  final  String BASE_URL = "https://raw.githubusercontent.com/";
    public static  CryptoAPI getCryptoApi(){
        return RetrofitClient.getClient(BASE_URL).create(CryptoAPI.class);
    }
}
