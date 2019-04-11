package com.kingscoder.iut_hackathon.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.kingscoder.iut_hackathon.R;


/**
 * A simple {@link ForgotPassFragment} subclass.
 */
public class ForgotPassFragment extends Fragment {


    private View rootView;
    private TextView backTV, resultTV;
    private EditText resetEmailET;
    private Button resetPassButton;
    private FirebaseAuth firebaseAuth;
    private ProgressBar resetPassProgressBar;

    public ForgotPassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.forgot_pass_fragment_, container, false);
        initialization();
        return rootView;
    }

    private void initialization() {

        firebaseAuth = FirebaseAuth.getInstance();

        backTV = rootView.findViewById(R.id.back_textview);
        resultTV = rootView.findViewById(R.id.result_textview);
        resetEmailET = rootView.findViewById(R.id.reset_email_edittext);
        resetPassButton = rootView.findViewById(R.id.reset_pass_button);
        resetPassProgressBar = rootView.findViewById(R.id.resetPassProgressBar);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        resetEmailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        resetPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassWithEmail();
            }
        });
    }

    private void resetPassWithEmail() {
        String email = resetEmailET.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            resetEmailET.setError("Enter a valid email address!");
        } else {
            resetPassProgressBar.setVisibility(View.VISIBLE);
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    resetPassProgressBar.setVisibility(View.INVISIBLE);
                    if (task.isSuccessful()){

                        resultTV.setText("We have send you an email for reset your password!");
                    } else {
                        Toast.makeText(getActivity(), "Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(resetEmailET.getText())){
            resetPassButton.setEnabled(true);
            resetPassButton.setTextColor(Color.rgb( 255,255, 255));
        }else {
            resetPassButton.setEnabled(false);
            resetPassButton.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }
}
