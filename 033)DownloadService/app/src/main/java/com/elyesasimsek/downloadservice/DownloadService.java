package com.elyesasimsek.downloadservice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private ExecutorService executorService;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String result = downloadData("https://anizle.com/");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Result: " + result);
                    }
                });
            }
        });;
        return  START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

    private String downloadData(String urlString){
        String result = "";
        URL url;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int data = inputStreamReader.read();
            while (data != -1){
                char current = (char) data;
                result += current;
                data = inputStreamReader.read();
            }
        }catch (Exception e){
            e.printStackTrace();
            result = "Failed";
        }finally {
            {
                if (httpURLConnection != null){
                    httpURLConnection.disconnect();
                }
            }
        }
        return  result;
    }

   /* @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AsyncTaskingClass asyncTaskingClass = new AsyncTaskingClass();
        try {
            asyncTaskingClass.execute("http://atilsamancioglu.com/").get();
        } catch (Exception e){
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    class AsyncTaskingClass extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection = null;
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();
                while ( data != -1){
                    char current = (char) data;
                    result += current;
                    data = inputStreamReader.read();
                }
                return result;
            } catch (Exception e){
                e.printStackTrace();
                return "Failed";
            }
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            System.out.println("Result: " + string);
        }
    }*/
}
