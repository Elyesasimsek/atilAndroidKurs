package com.example.a021retrofitjava.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.a021retrofitjava.R;
import com.example.a021retrofitjava.adapter.RecyclerViewAdapter;
import com.example.a021retrofitjava.databinding.ActivityMainBinding;
import com.example.a021retrofitjava.model.CryptoModel;
import com.example.a021retrofitjava.service.ApiUtils;
import com.example.a021retrofitjava.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<CryptoModel> cryptoModelArrayList;
    private CryptoAPI cryptoAPI;
    private Retrofit retrofit;
    private RecyclerViewAdapter recyclerViewAdapter;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.toolbar.setTitle("Crypto");
        setSupportActionBar(binding.toolbar);

        cryptoAPI = ApiUtils.getCryptoApi();
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        loadData();

    }

    private  void  loadData(){
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse));
    }

    private void handleResponse(List<CryptoModel> cryptoModelList){
        cryptoModelArrayList = new ArrayList<>(cryptoModelList);
        binding.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,cryptoModelArrayList);
        binding.rv.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}