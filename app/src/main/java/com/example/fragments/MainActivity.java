package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ImageButton homeButton, listButton, menuOptions, loginButton;
    private Home home;
    private List list;
    private SearchedProducts searchedProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.homeButton = findViewById(R.id.imageButton1);
        this.listButton = findViewById(R.id.imageButton2);
        this.menuOptions = findViewById(R.id.imageButton_menu);
        this.loginButton = findViewById(R.id.imageButton4);

        this.home = new Home();
        this.list = new List();


        FragmentTransaction principal = getSupportFragmentManager().beginTransaction();
        principal.add(R.id.fragment_principal, home);
        principal.commit();

        this.homeButton.setOnClickListener(view -> {
            setAnimation(view);
            home = new Home();
            FragmentTransaction principal1 = getSupportFragmentManager().beginTransaction();
            principal1.replace(R.id.fragment_principal, home);
            principal1.commit();
        });

        this.listButton.setOnClickListener(view -> {
            setAnimation(view);
            list = new List();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_principal, list);
            transaction.commit();
        });

        this.menuOptions.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, menuOptions);
            popupMenu.getMenuInflater().inflate(R.menu.menu_list, popupMenu.getMenu());
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        });

        this.loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);

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