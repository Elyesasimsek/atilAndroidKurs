package com.example.catchtheonepiece;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.example.catchtheonepiece.databinding.ActivityCatchBinding;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class CatchActivity extends AppCompatActivity {

    private ActivityCatchBinding design;
    private int score;
    private ImageView[] imageViewArray;
    private Handler handler;
    private Runnable runnable;
    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        design = DataBindingUtil.setContentView(this, R.layout.activity_catch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        character = (Character) getIntent().getSerializableExtra("character");
        imageViewArray = new ImageView[]{design.imageView1, design.imageView2, design.imageView3, design.imageView4, design.imageView5, design.imageView6, design.imageView7, design.imageView8, design.imageView9};



        for (ImageView imageView: imageViewArray){
            String url = "https://elyesasimsek.com/onepiece/image/" + character.getCharacterImage();
            Picasso.get().load(url).into(imageView);
        }

        hideImages();


        score = 0;

        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                design.textViewTime.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                design.textViewTime.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image:imageViewArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(CatchActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CatchActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(CatchActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void increaseScore(View view){
        score++;
        design.textViewScore.setText("Score: " + score);
    }

    public void hideImages(){
        handler = Handler.createAsync(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageViewArray){
                    image.setVisibility(View.INVISIBLE);
                  }
                Random random = new Random();
                int i = random.nextInt(9);
                imageViewArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 500);
           }
        };
        handler.post(runnable);
    }
}