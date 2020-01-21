package com.vfguille.inventoryjetpack.ui.dash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.ui.dash.dependencies.DependencyActivity;
import com.vfguille.inventoryjetpack.ui.dash.product.ProductActivity;
import com.vfguille.inventoryjetpack.ui.dash.sections.SectionActivity;


public class DashBoardFragment extends Fragment {

    public static final String TAG = "DashBoardFragment";
    Button btDependencies;
    Button btSecion;
    Button btProduct;

    public DashBoardFragment() { }

    // TODO: Rename and change types and number of parameters
    public static DashBoardFragment newInstance() {
        return new DashBoardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_dash_board_material, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btDependencies = view.findViewById(R.id.btnDependencies);
        btSecion = view.findViewById(R.id.btnSections);
        btProduct = view.findViewById(R.id.btnProducts);


        btDependencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DependencyActivity.class));
            }
        });

        btSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SectionActivity.class));
            }
        });

        btProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProductActivity.class));
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
