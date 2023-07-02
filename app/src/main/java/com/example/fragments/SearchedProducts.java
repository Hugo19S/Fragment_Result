package com.example.fragments;


import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchedProducts extends Fragment {
    private final String searchedTerm;
    private final boolean control;
    private final List<String> searchedNamesProduct = new ArrayList<>();
    private final List<Integer> searchedPhotoProduct = new ArrayList<>();
    private final List<List<Double>> priceOfProducts = new ArrayList<>();
    private final List<String> categoryOfProducts = new ArrayList<>();

    private Product product;

    public SearchedProducts(String searchedTerm, boolean control) {
        this.control = control;
        this.searchedTerm = searchedTerm;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searched_products, container, false);

        product = new Product(requireContext());
        generatorProducts(view);

        return view;
    }

    public void generatorProducts(View view) {

        searchedProducts();
        if (this.searchedPhotoProduct.size() == 0) {
            ProductNotFound notFound = new ProductNotFound();
            FragmentTransaction principal = requireActivity().getSupportFragmentManager().beginTransaction();
            principal.replace(R.id.fragment_principal, notFound);
            principal.commit();

        } else {
            List<List<Double>> priceProductList = new ArrayList<>();
            for (int i = 0; i < searchedNamesProduct.size(); i++) {
                priceProductList.add(this.priceOfProducts.get(i));
            }

            GridView gridView = view.findViewById(R.id.gridview_searchPage);
            GridAdapter adapter = new GridAdapter(requireContext(),searchedNamesProduct, searchedPhotoProduct, 1, priceProductList, categoryOfProducts, requireActivity().getSupportFragmentManager());
            gridView.setAdapter(adapter);
        }
    }

    @SuppressLint("DiscouragedApi")
    public void searchedProducts() {

        for (int i = 0; i < product.getProductName().size(); i++) {
            String item = "";
            String name = "";
            int resourceId = 0;
            List<Double> prices = new ArrayList<>();
            if (this.control) name = product.getProductName().get(i);
            else name = product.getProductCategorty().get(i);

            if (name.toLowerCase().contains(this.searchedTerm.toLowerCase())) {
                String photo = product.getProductImage().get(i).toString();
                if (photo.startsWith("R.drawable.")) {
                    String resourceName = photo.substring("R.drawable.".length());
                    resourceId = getResources().getIdentifier(resourceName, "drawable", requireContext().getPackageName());
                }
                item = product.getProductName().get(i) + "\n" + "€ " + product.getProductPrice().get(i).get(0) + "\n" + "emb: " + product.getProductEmb().get(i);
                for (int j = 0; j < product.getProductPrice().get(0).size(); j++) {
                    prices.add(product.getProductPrice().get(i).get(j));
                }

                String category = product.getProductCategorty().get(i) + "\n" + product.getProdcutDescription().get(i);

                this.searchedPhotoProduct.add(resourceId);
                this.priceOfProducts.add(prices);
                this.searchedNamesProduct.add(item);
                this.categoryOfProducts.add(category);
            }
        }
    }
}