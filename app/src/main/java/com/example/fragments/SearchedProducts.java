package com.example.fragments;


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
    private boolean control;
    private final List<String> searchedNamesProduct = new ArrayList<>();
    private final List<Integer> searchedPhotoProduct = new ArrayList<>();
    private final List<double[]> priceOfProducts = new ArrayList<>();
    private final List<String> categoryOfProducts = new ArrayList<>();

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

        generatorProducts(view);

        return view;
    }

    public void generatorProducts(View view) {

        readJson();
        if (this.searchedNamesProduct.size() == 0) {
            ProductNotFound notFound = new ProductNotFound();
            FragmentTransaction principal = requireActivity().getSupportFragmentManager().beginTransaction();
            principal.replace(R.id.fragment_principal, notFound);
            principal.commit();

        } else {
            String[] finalSearchedNamesProduct = searchedNamesProduct.toArray(new String[0]);
            int[] finalSearchedPhotoProduct = new int[searchedPhotoProduct.size()];
            double[][] priceProductArray = new double[this.priceOfProducts.size()][this.priceOfProducts.get(0).length];
            String[] finalCategories = this.categoryOfProducts.toArray(new String[0]);

            for (int i = 0; i < finalSearchedPhotoProduct.length; i++) {
                finalSearchedPhotoProduct[i] = searchedPhotoProduct.get(i);
                priceProductArray[i] = this.priceOfProducts.get(i);
            }

            GridView gridView = view.findViewById(R.id.gridview_searchPage);
            GridAdapter adapter = new GridAdapter(requireContext(), finalSearchedNamesProduct, finalSearchedPhotoProduct,
                    1, priceProductArray, finalCategories, getChildFragmentManager());
            gridView.setAdapter(adapter);
        }
    }

    public void readJson() {

        JSONArray jsonArray;
        try {
            AssetManager assetManager = requireContext().getAssets();
            InputStream inputStream = assetManager.open("produtos.json");

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

            jsonArray = new JSONArray(stringBuilder.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String item;
                String name;

                if (this.control) name = jsonObject.get("name").toString();
                else name = jsonObject.get("category").toString();

                if (name.toLowerCase().contains(this.searchedTerm.toLowerCase())) {
                    String photo = jsonObject.get("img").toString();
                    double[] price = new double[3];
                    price[0] = Double.parseDouble(jsonObject.get("price_continente").toString());
                    price[1] = Double.parseDouble(jsonObject.get("price_pingoDoce").toString());
                    price[2] = Double.parseDouble(jsonObject.get("price_intermarche").toString());

                    double[] lowestPrice = Arrays.stream(price).sorted().toArray();

                    item = jsonObject.get("name") + "\n" + "€ " + lowestPrice[0] + "\n" + "emb: " + jsonObject.get("embalagem");
                    int resourceId = 0;

                    String category = jsonObject.getString("category") + "\n" + jsonObject.getString("description");

                    if (photo.startsWith("R.drawable.")) {
                        String resourceName = photo.substring("R.drawable.".length());
                        resourceId = getResources().getIdentifier(resourceName, "drawable", requireContext().getPackageName());
                    }
                    this.searchedNamesProduct.add(item);
                    this.searchedPhotoProduct.add(resourceId);
                    this.priceOfProducts.add(price);
                    this.categoryOfProducts.add(category);
                }

            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}