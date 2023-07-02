package com.example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

public class List extends Fragment {
    GridView gridViewList;
    GridAdapter gridAdapterList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        java.util.List<String> namesProduct = java.util.List.of("Leite Continente Mercearias Magro\n€0.99",
                "Agua Continente Bebidas 1L6\n€2.49");

        java.util.List<Integer> photoProduct = java.util.List.of(
                R.drawable.leite,
                R.drawable.agua);
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        gridViewList = view.findViewById(R.id.gridView_list);
        gridAdapterList = new GridAdapter(requireContext(), namesProduct, photoProduct, 3, null,
                null, requireActivity().getSupportFragmentManager());
        gridViewList.setAdapter(gridAdapterList);
        // Inflate the layout for this fragment
        return view;
    }
}