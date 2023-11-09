package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends AppCompatActivity {
    private TextView goToSignIn;
    private EditText fullNameet,phoneNumet,ncinet,emailet,passwordet,passwordConfet;
    private Button signUpBtn;
    private String fullNameS,phoneNumS,ncinS,emailS,passwordS,passwordConfS;

    private static final String EMAIL_REGEX =
           "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        fullNameet = findViewById(R.id.fullName);
        phoneNumet = findViewById(R.id.phoneNum);
        ncinet     = findViewById(R.id.ncin);
        emailet    = findViewById(R.id.email);
        passwordet = findViewById(R.id.password);
        passwordConfet = findViewById(R.id.passwordConf);
        goToSignIn = findViewById(R.id.goToSignIn);
        signUpBtn = findViewById(R.id.signUpBtn);

        goToSignIn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
        });
       firebaseAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(v -> {

            if (validate()) {
                //Toast.makeText(this, "valide", Toast.LENGTH_SHORT).show();
                firebaseAuth.createUserWithEmailAndPassword(emailS,passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "account created", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SignUpActivity.this, "register failed", Toast.LENGTH_SHORT).show();
                        }
                        } 
                    
                });
            }
       });
    }

      private Boolean validate() {
         boolean result=false;
         fullNameS=fullNameet.getText().toString();
       phoneNumS=phoneNumet.getText().toString();
         ncinS=ncinet.getText().toString();
         emailS=emailet.getText().toString();
         passwordS=passwordet.getText().toString();
         passwordConfS=passwordConfet.getText().toString();
         if (fullNameS.isEmpty()||fullNameS.length()<7)
         {
             fullNameet.setError("full name not valide");
         } else if (!isValidEmail(emailS)) {
             emailet.setError("email not valide");
         } else if (phoneNumS.isEmpty()||phoneNumS.length()!=8) {
             phoneNumet.setError("phone not valide");
         } else if (ncinS.isEmpty()||ncinS.length()!=8) {
             ncinet.setError("ncin not valide");
         }else if (passwordS.isEmpty()||passwordS.length()<7){
             passwordet.setError("password invalide");
         } else if (!passwordS.equals(passwordConfS)||passwordConfS.isEmpty()) {
             passwordConfet.setError("password not match");
         } else {
             result=true;
         }
         return result;
     }
    public  boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}