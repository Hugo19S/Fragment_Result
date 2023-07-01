package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ImageButton homeButton, listButton;
    private Home home;
    private List list;
    private SearchedProducts searchedProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeButton = findViewById(R.id.imageButton1);
        listButton = findViewById(R.id.imageButton2);
        home = new Home();
        list = new List();

        FragmentTransaction principal = getSupportFragmentManager().beginTransaction();
        principal.add(R.id.fragment_principal, home);
        principal.commit();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation(v);
                home = new Home();
                FragmentTransaction principal = getSupportFragmentManager().beginTransaction();
                principal.replace(R.id.fragment_principal, home);
                principal.commit();
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimation(v);
                list = new List();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_principal, list);
                transaction.commit();
            }
        });

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

    }

    public void addProductToList(View view) {
        Toast.makeText(this, "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void setAnimation(View view) {
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(300);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchedProducts = new SearchedProducts(query, true);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_principal, searchedProducts);
        transaction.commit();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}