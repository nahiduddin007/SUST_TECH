package com.kingscoder.iut_hackathon.activities;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.kingscoder.iut_hackathon.R;
import com.kingscoder.iut_hackathon.fragments.SignInFragment;

public class RegisterActivity extends AppCompatActivity {

    private FrameLayout parentFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialization();
    }

    private void initialization() {
        parentFrameLayout = findViewById(R.id.register_frame_layout);

        setFragment(new SignInFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
