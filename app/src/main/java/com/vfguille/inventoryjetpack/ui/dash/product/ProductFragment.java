package com.vfguille.inventoryjetpack.ui.dash.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vfguille.inventoryjetpack.R;

/*
    Fragment principal que contiene otros fragments.
 */

public class ProductFragment extends Fragment {


    public static final String TAG = "ProductFragment";
    private FloatingActionButton floatingActionButton;
    private BottomNavigationView bottomNavigationView;
    private BottomAppBar bottomAppBar;
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener listener;

    public ProductFragment() {
    }

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.products));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = getActivity().findViewById(R.id.fabSection);
        floatingActionButton.hide();
        bottomNavigationView = view.findViewById(R.id.menuProduct);
        bottomAppBar = view.findViewById(R.id.babSection);

        initNavigation();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFragment(ProductInfoFragment.newInstance());
    }

    private void initNavigation(){
        listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.itemDescription:
                        fragment = ProductDescriptionFragment.newInstance();
                        break;
                    case R.id.itemInfo:
                        fragment = ProductInfoFragment.newInstance();
                        break;
                    case R.id.itemMap:
                        fragment = ProductMapFragment.newInstance();
                        break;
                }
                loadFragment(fragment);
                return true;
            }
        };
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
    }


    /**
     * Se encarga de gestionar los Fragments del BottomNavigationView dentro de otro fragment.
     * @param fragment
     * @return
     */
    private void loadFragment(Fragment fragment) {
        if (fragment != null){
            getChildFragmentManager().beginTransaction().replace(R.id.frameProduct, fragment).commit();
        }
    }

}
