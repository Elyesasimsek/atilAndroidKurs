package com.example.catchtheonepiece;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.catchtheonepiece.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding design;
    private CatchAdapter adapter;
    private List<Character> onePieceCharacterList;
    private CharacterDAOInterface characterDIF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        design = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        characterDIF = ApiUtils.getFilmlerDaoInterface();
        design.rv.setHasFixedSize(true);
        design.rv.setLayoutManager(new LinearLayoutManager(this));

        allCharacters();
    }

    public void allCharacters(){
        characterDIF.allCharacters().enqueue(new Callback<OnePieceCharacterReply>() {
            @Override
            public void onResponse(Call<OnePieceCharacterReply> call, Response<OnePieceCharacterReply> response) {
                onePieceCharacterList = response.body().getCharacters();
                adapter = new CatchAdapter(MainActivity.this, onePieceCharacterList);
                design.rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<OnePieceCharacterReply> call, Throwable t) {

            }
        });
    }
}