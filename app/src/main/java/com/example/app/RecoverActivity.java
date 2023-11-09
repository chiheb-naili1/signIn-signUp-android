package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class RecoverActivity extends AppCompatActivity {

    private Button changePass;
    private Button backToSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        changePass = findViewById(R.id.changePass);
        backToSignIn = findViewById(R.id.backToSignIn);

        backToSignIn.setOnClickListener(v -> {
            startActivity(new Intent(RecoverActivity.this, SignInActivity.class));
        });

        changePass.setOnClickListener(v -> {
            showToast("Password changed successfully!");
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
