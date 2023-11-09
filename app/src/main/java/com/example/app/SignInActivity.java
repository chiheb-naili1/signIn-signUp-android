package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    private TextView forgetPass;
    private TextView goToSignUp;
    private Button signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        forgetPass = findViewById(R.id.forgetPass);
        goToSignUp = findViewById(R.id.goToSignUp);
        signIn = findViewById(R.id.signIn);


        goToSignUp.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
        });

        forgetPass.setOnClickListener(v -> {
            startActivity((new Intent(SignInActivity.this,RecoverActivity.class)));
        });

        signIn.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, HomeScrenn.class));
        });
    }
}