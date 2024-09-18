package com.example.javaworkmanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Data data = new Data.Builder().putInt("intKey", 1).build();

        Constraints constraints = new Constraints.Builder()/*.setRequiredNetworkType(NetworkType.CONNECTED)*/.setRequiresCharging(false).build();

        //WorkRequest workRequest = new OneTimeWorkRequest.Builder(RefreshDatabase.class).setConstraints(constraints).setInputData(data)/*.setInitialDelay(5, TimeUnit.MINUTES).addTag("myTag")*/.build();

        WorkRequest workRequest = new PeriodicWorkRequest.Builder(RefreshDatabase.class, 15, TimeUnit.MINUTES).setConstraints(constraints).setInputData(data).build();

        WorkManager.getInstance(this).enqueue(workRequest);

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo.getState() == WorkInfo.State.RUNNING){
                    System.out.println("Running");
                } else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    System.out.println("Succeded");
                } else if (workInfo.getState() == WorkInfo.State.FAILED) {
                    System.out.println("Failed");
                }
            }
        });

        //WorkManager.getInstance(this).cancelAllWork();

        //Chaining
    /*    OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(RefreshDatabase.class).setInputData(data).setConstraints(constraints).build();
        WorkManager.getInstance(this).beginWith(oneTimeWorkRequest).then(oneTimeWorkRequest).then(oneTimeWorkRequest).enqueue();*/
    }
}