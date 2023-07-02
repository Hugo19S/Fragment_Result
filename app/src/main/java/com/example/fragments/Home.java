package com.example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    private Product product;
    private List<String> namesProduct;
    private List<String> categoryOfProducts;
    private List<Integer> photoProduct;
    private List<List<Double>> pricesProduct;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        namesProduct = new ArrayList<>();
        categoryOfProducts = new ArrayList<>();
        photoProduct = new ArrayList<>();
        pricesProduct = new ArrayList<>();
        this.product = new Product(requireContext());
        for (int i = 0; i < 4; i++) {

            String item = "";
            int resourceId = 0;
            List<Double> prices = new ArrayList<>();
            item = product.getProductName().get(i) + "\n" + "€ " + product.getProductPrice().get(i).get(0) + "\n" + "emb: " + product.getProductEmb().get(i);
            namesProduct.add(item);
            categoryOfProducts.add(product.getProductCategorty().get(i)  + "\n" + product.getProdcutDescription().get(i));
            String photo = product.getProductImage().get(i);
            if (photo.startsWith("R.drawable.")) {
                String resourceName = photo.substring("R.drawable.".length());
                resourceId = getResources().getIdentifier(resourceName, "drawable", requireContext().getPackageName());
            }
            photoProduct.add(resourceId);
            for (int j = 0; j < product.getProductPrice().get(0).size(); j++) {
                prices.add((product.getProductPrice().get(i).get(j)));
            }
            pricesProduct.add(prices);
        }
        List<String> namesCategories = List.of("Talho", "Peixaria", "Bebidas", "Padaria", "Casa", "Higiene", "Mercearias", "Frutas");
        List<Integer> photoCategories = List.of(R.drawable.talho, R.drawable.peixaria, R.drawable.agua, R.drawable.padaria, R.drawable.casa, R.drawable.higiene, R.drawable.mercearias, R.drawable.all_frutas);


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        GridView gridViewMostSearched = view.findViewById(R.id.gridview_mainPage_1);
        GridAdapter gridAdapterMostSearched = new GridAdapter(requireContext(), namesProduct, photoProduct,
                1, pricesProduct, categoryOfProducts, requireActivity().getSupportFragmentManager());
        gridViewMostSearched.setAdapter(gridAdapterMostSearched);

        GridView gridViewCategories = view.findViewById(R.id.gridview_mainPage_2);
        GridAdapter gridAdapterCategories = new GridAdapter(requireContext(), namesCategories, photoCategories,
                2, null, null, requireActivity().getSupportFragmentManager());
        gridViewCategories.setAdapter(gridAdapterCategories);

        gridViewCategories.setOnItemClickListener((paren, view1, position, id)->
                nextFrame(namesCategories.get(position)));

        return view;
    }

    private void nextFrame(String namesCategory) {
        SearchedProducts searchedProducts = new SearchedProducts(namesCategory, false);
        FragmentTransaction principal = requireActivity().getSupportFragmentManager().beginTransaction();
        principal.replace(R.id.fragment_principal, searchedProducts);
        principal.commit();
    }
}