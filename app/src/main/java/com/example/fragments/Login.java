package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SigIn sigIn = new SigIn();

        FragmentTransaction principal = getSupportFragmentManager().beginTransaction();
        principal.add(R.id.fragment_login, sigIn);
        principal.commit();
    }


}