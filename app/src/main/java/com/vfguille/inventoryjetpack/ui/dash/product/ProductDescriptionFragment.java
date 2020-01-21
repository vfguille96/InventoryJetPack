package com.vfguille.inventoryjetpack.ui.dash.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.vfguille.inventoryjetpack.R;


public class ProductDescriptionFragment extends Fragment {


    public ProductDescriptionFragment() {
        // Required empty public constructor
    }


    public static ProductDescriptionFragment newInstance() {
        ProductDescriptionFragment fragment = new ProductDescriptionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_description, container, false);
    }


}
