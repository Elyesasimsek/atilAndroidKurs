package com.elyesasimsek.fourscarecloneparse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.elyesasimsek.fourscarecloneparse.databinding.ActivitySignUpBinding;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ParseUser parseUser = ParseUser.getCurrentUser();
        if (parseUser != null){
            clickLocation();
        }
    }

    public void signUp(View view){
        ParseUser user = new ParseUser();
        user.setUsername(binding.editTextSignUpActivityUserName.getText().toString());
        user.setPassword(binding.editTextSignUpActivityPassword.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "User Signed Up!!!", Toast.LENGTH_LONG).show();
                    clickLocation();
                }
            }
        });
    }

    private void clickLocation(){
        Intent intent = new Intent(getApplicationContext(), LocationsActivity.class);
        finish();
        startActivity(intent);
    }

    public void signIn(View view){
        ParseUser.logInInBackground(binding.editTextSignUpActivityUserName.getText().toString(), binding.editTextSignUpActivityPassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Welcome: " + user.getUsername(), Toast.LENGTH_LONG).show();
                    clickLocation();
                }
            }
        });
    }
}