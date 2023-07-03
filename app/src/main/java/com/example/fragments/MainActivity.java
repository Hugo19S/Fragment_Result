package com.example.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
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

        this.searchView = findViewById(R.id.searchView);
        this.searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onBackPressed() {
        // Verificar se o fragmento atual é o productDetails
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_principal) instanceof ProductDetails) {
            // Voltar para o fragmento anterior
            getSupportFragmentManager().popBackStack();
        }
        else {
            //cria a caixa de dialogo
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Deseja sair?")
                    .setPositiveButton("Sim", (dialog, id) -> System.exit(0))
                    .setNegativeButton("Não", (dialog, id) -> {/*Não faz nada*/});

            // Create the AlertDialog object and return it
            builder.create().show();

        }
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
        //fecha o teclado
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.searchView.getWindowToken(), 0);

        // Remover o foco do SearchView
        this.searchView.clearFocus();

        this.searchedProducts = new SearchedProducts(query, true);

        //Coloca o searchedProducts no fragment_principal
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