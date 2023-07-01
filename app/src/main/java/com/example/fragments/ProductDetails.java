package com.example.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ProductDetails extends Fragment {

    private int forImage;
    private String nameOfProduct;
    private double[] price;
    private  String description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.forImage = getArguments().getInt("imageResource");
            this.nameOfProduct = getArguments().getString("text");
            this.price = getArguments().getDoubleArray("price");
            this.description = getArguments().getString("description");
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        ImageView imageView = view.findViewById(R.id.imageView_product);
        TextView textView = view.findViewById(R.id.textView6);
        TextView textView9 = view.findViewById(R.id.textView9);
        TextView textView11 = view.findViewById(R.id.textView11);
        TextView textView12 = view.findViewById(R.id.textView12);
        TextView textView5 = view.findViewById(R.id.textView5);

        imageView.setImageResource(this.forImage);
        textView.setText(this.nameOfProduct);
        textView9.setText("€" + this.price[1]);
        textView11.setText("€" + this.price[0]);
        textView12.setText("€" + this.price[2]);
        textView5.setText("Categoria do produto: " + this.description);

        if (price[1] < price[2] && price[1] < price[0]) {
            textView9.setTextColor(Color.GREEN);
            textView9.setTypeface(null, Typeface.BOLD);
        } else if (price[2] < price[0] && price[2] < price[1]) {
            textView12.setTextColor(Color.GREEN);
            textView12.setTypeface(null, Typeface.BOLD);
        } else {
            textView11.setTextColor(Color.GREEN);
            textView11.setTypeface(null, Typeface.BOLD);
        }

        // Inflate the layout for this fragment
        return view;
    }
}