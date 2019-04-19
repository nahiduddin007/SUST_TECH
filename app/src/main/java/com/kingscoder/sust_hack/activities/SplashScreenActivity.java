package com.kingscoder.sust_hack.activities;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kingscoder.sust_hack.R;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();

        SystemClock.sleep(500);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            Intent intent = new Intent(SplashScreenActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, PatientMainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
