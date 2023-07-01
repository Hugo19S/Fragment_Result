package com.example.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class Home extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] namesProduct = {
                "Leite Magro\n€0.99\nemb: 1 lt",
                "Agua 1L*6\n€0.59\nemb: 1.5 lt",
                "Café\n€0.89\nemb: 1 lt",
                "Açucar\n€0.69\nemb: 1 kg"
        };




        String[] categoryOfProducts = {
                "Bebidas",
                "Bebidas",
                "Mercearias",
                "Mercearias"
        };

        String[] namesCategories = {
                "Talho",
                "Peixaria",
                "Bebidas",
                "Padaria",
                "Casa",
                "Higiene",
                "Mercearias",
                "Frutas"
        };

        int[] photoProduct = {
                R.drawable.leite,
                R.drawable.agua,
                R.drawable.cafe,
                R.drawable.acucar
        };

        int[] photoCategories = {
                R.drawable.talho,
                R.drawable.peixaria,
                R.drawable.agua,
                R.drawable.padaria,
                R.drawable.casa,
                R.drawable.higiene,
                R.drawable.mercearias,
                R.drawable.all_frutas
        };

        double[][] price = {{0.99, 1.99, 2.99}, {2.49, 1.49, 0.59}, {1.49, 0.89, 3.59}, {0.69, 0.29, 0.95}};

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        GridView gridViewMostSearched = view.findViewById(R.id.gridview_mainPage_1);
        GridAdapter gridAdapterMostSearched = new GridAdapter(requireContext(), namesProduct, photoProduct, 1, price, categoryOfProducts);
        gridViewMostSearched.setAdapter(gridAdapterMostSearched);

        GridView gridViewCategories = view.findViewById(R.id.gridview_mainPage_2);
        GridAdapter gridAdapterCategories = new GridAdapter(requireContext(), namesCategories, photoCategories, 2, null, null);
        gridViewCategories.setAdapter(gridAdapterCategories);

        gridViewCategories.setOnItemClickListener((paren, view1, position, id)-> nextFrame(namesCategories[position], false));

        return view;
    }

    private void nextFrame(String namesCategory, boolean control) {
        SearchedProducts searchedProducts = new SearchedProducts(namesCategory, control);
        FragmentTransaction principal = requireActivity().getSupportFragmentManager().beginTransaction();
        principal.replace(R.id.fragment_principal, searchedProducts);
        principal.commit();
    }
}