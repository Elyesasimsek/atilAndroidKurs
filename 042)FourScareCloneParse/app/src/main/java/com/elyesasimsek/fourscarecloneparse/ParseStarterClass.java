package com.elyesasimsek.fourscarecloneparse;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("7Jp270nXi1ZRQbCumLGJ8q35kg4UXApaFS6c5qB4")
                .clientKey("A0fraPEfQmvnlefZCrO2cNSOD8cGAy2XuIvMqEee")
                .server("https://parseapi.back4app.com/")
                .build());
    }
}
