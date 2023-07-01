package com.example.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class GridAdapter extends BaseAdapter {

    private final FragmentManager fragmentManager;
    private final String[] imageNames;
    private final int[] imagePhotos;
    private final int isProduct;
    private final double[][] price;
    private final String[] category;
    private final LayoutInflater layoutInflater;
    private final Context context;

    public GridAdapter(Context context, String[] imageNames, int[] imagePhotos,
                       int isProduct, double[][] price, String[] category, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.imageNames = imageNames;
        this.imagePhotos = imagePhotos;
        this.isProduct = isProduct;
        this.price = price;
        this.category = category;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imageNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("CutPasteId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            if (isProduct == 1) {
                convertView = layoutInflater.inflate(R.layout.itens, parent, false);
                holder.textView = convertView.findViewById(R.id.textView_items);
                holder.imageView = convertView.findViewById(R.id.imageView_items);

                LinearLayout layout = convertView.findViewById(R.id.linearLayout3);
                layout.setOnClickListener(v -> {

                    Bundle bundle = new Bundle();
                    bundle.putInt("imageResource", imagePhotos[position]);
                    bundle.putString("text", holder.textView.getText().toString());
                    bundle.putDoubleArray("price", price[position]);
                    bundle.putString("description", category[position]);

                    ProductDetails details = new ProductDetails();
                    details.setArguments(bundle);

                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment_principal, details);
                    transaction.commit();
                });


            } else if (isProduct == 2) {
                convertView = layoutInflater.inflate(R.layout.categories, parent, false);
                holder.textView = convertView.findViewById(R.id.textView_categories);
                holder.imageView = convertView.findViewById(R.id.imageView_categories);
            } else if (isProduct == 3) {
                convertView = layoutInflater.inflate(R.layout.product_list, parent, false);
                holder.textView = convertView.findViewById(R.id.textView_list);
                holder.imageView = convertView.findViewById(R.id.imageView_product_list);

                View layout = convertView.findViewById(R.id.linearLayout2);
                final ImageButton imageButton = convertView.findViewById(R.id.checkBox);
                final TextView textView = convertView.findViewById(R.id.textView_list);
                imageButton.setOnClickListener(v -> {
                    layout.setBackgroundColor(Color.GRAY);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.disable));
                    imageButton.setImageResource(R.drawable.checkbox_true_foreground);
                });
            }

            assert convertView != null;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(imageNames[position]);
        holder.imageView.setImageResource(imagePhotos[position]);

        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}