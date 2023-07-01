package com.example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
                "Bebidas\nO leite UHT Mimosa Bem Essencial Meio-Gordo oferece a riqueza nutricional do leite com baixo teor de gordura .",
                "Bebidas\nÁgua mineral proveniente de nascente portuguesa. A embalagem tem incorporado 25% de plástico reciclado de forma a diminuir o consumo de matéria prima virgem dando uma nova vida às embalagens.",
                "Mercearias\nCafé em moagem fina indicado para máquinas expresso. Uma agradável experiência, incapaz de deixar alguém indiferente.",
                "Mercearias\nAçúcar branco granulado para adoçar a sua refeição."
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
        GridAdapter gridAdapterMostSearched = new GridAdapter(requireContext(), namesProduct, photoProduct,
                1, price, categoryOfProducts, requireActivity().getSupportFragmentManager());
        gridViewMostSearched.setAdapter(gridAdapterMostSearched);

        GridView gridViewCategories = view.findViewById(R.id.gridview_mainPage_2);
        GridAdapter gridAdapterCategories = new GridAdapter(requireContext(), namesCategories, photoCategories,
                2, null, null, requireActivity().getSupportFragmentManager());
        gridViewCategories.setAdapter(gridAdapterCategories);

        gridViewCategories.setOnItemClickListener((paren, view1, position, id)->
                nextFrame(namesCategories[position]));

        return view;
    }

    private void nextFrame(String namesCategory) {
        SearchedProducts searchedProducts = new SearchedProducts(namesCategory, false);
        FragmentTransaction principal = requireActivity().getSupportFragmentManager().beginTransaction();
        principal.replace(R.id.fragment_principal, searchedProducts);
        principal.commit();
    }
}