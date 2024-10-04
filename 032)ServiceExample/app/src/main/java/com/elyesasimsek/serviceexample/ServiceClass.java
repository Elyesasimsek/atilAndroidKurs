package com.elyesasimsek.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServiceClass extends Service {
    private static final String TAG = "ServiceClass";
    private ExecutorService executorService;
    private Handler handler;
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String currentThread = Thread.currentThread().getName();
        Log.d(TAG, "onCreate: " + currentThread);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        String currentThread = Thread.currentThread().getName();
        Log.d(TAG, "onDestroy: " + currentThread);
        executorService.shutdown();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String currentThread = Thread.currentThread().getName();
        Log.d(TAG, "onStartCommand: " + currentThread);

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        doAsyncWork(3000);
        return super.onStartCommand(intent, flags, startId);
    }

    private void doAsyncWork(int sleepTime){
        Future<?> future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                String currentThread = Thread.currentThread().getName();
                Log.d(TAG, "run: " + currentThread);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String currentThread = Thread.currentThread().getName();
                        Log.d(TAG, "run: " + currentThread);
                    }
                });
            }
        });


    }
}
