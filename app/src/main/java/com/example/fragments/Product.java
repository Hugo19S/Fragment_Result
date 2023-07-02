package com.example.fragments;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

public class Product extends AppCompatActivity {

    List<String> productName;
    List<List<Double>> productPrice;
    List<String> productImage;
    List<String> productCategorty;
    List<String> productEmb;
    List<String> prodcutDescription;

    Context context;

    public Product(Context context) {
        this.context = context;

        this.productName = new ArrayList<>();
        this.productPrice = new ArrayList<>();
        this.productImage = new ArrayList<>();
        this.productCategorty = new ArrayList<>();
        this.productEmb = new ArrayList<>();
        this.prodcutDescription = new ArrayList<>();
        readJson(context);
    }

    public void readJson(Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("produtos.json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

            JSONArray jsonArray = new JSONArray(stringBuilder.toString());
            // Processar o JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                productName.add(jsonObject.getString("name"));
                productCategorty.add(jsonObject.getString("category"));
                productEmb.add(jsonObject.getString("embalagem"));
                productImage.add(jsonObject.getString("img"));
                List<Double> prices = new ArrayList<>();
                prices.add(Double.parseDouble(jsonObject.get("price_continente").toString()));
                prices.add(Double.parseDouble(jsonObject.get("price_pingoDoce").toString()));
                prices.add(Double.parseDouble(jsonObject.get("price_intermarche").toString()));
                Collections.sort(prices);
                productPrice.add(prices);
                prodcutDescription.add(jsonObject.getString("description"));

            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }

    public List<String> getProductName() {
        return productName;
    }

    public List<List<Double>> getProductPrice() {
        return productPrice;
    }

    public List<String> getProductImage() {
        return productImage;
    }

    public List<String> getProductCategorty() {
        return productCategorty;
    }

    public List<String> getProductEmb() {
        return productEmb;
    }

    public List<String> getProdcutDescription() {
        return prodcutDescription;
    }

    public Context getContext() {
        return context;
    }

}