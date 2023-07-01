package com.example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class ProductDetails extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString("chave");
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        ImageView imageView = view.findViewById(R.id.imageView_product);
        TextView textView = view.findViewById(R.id.textView6);
        //textView.setText(this.mParam1);
        TextView textView9 = view.findViewById(R.id.textView9);
        TextView textView11 = view.findViewById(R.id.textView11);
        TextView textView12 = view.findViewById(R.id.textView12);
        TextView textView5 = view.findViewById(R.id.textView5);

        FragmentTransaction principal = requireActivity().getSupportFragmentManager().beginTransaction();
        /*if (intent != null) {
            int imageResource = intent.getIntExtra("imageResource", 0);
            String text = intent.getStringExtra("text");
            double[] price = intent.getDoubleArrayExtra("price");
            String category = intent.getStringExtra("category");
            textView9.setText("€" + price[1]);
            textView11.setText("€" + price[0]);
            textView12.setText("€" + price[2]);
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

            if (text != null) textView.setText(text);
            if (imageResource != 0) imageView.setImageResource(imageResource);
            if (textView5 != null) textView5.setText("Categoria do produto: " + category);
        }*/

        // Inflate the layout for this fragment
        return view;
    }
}