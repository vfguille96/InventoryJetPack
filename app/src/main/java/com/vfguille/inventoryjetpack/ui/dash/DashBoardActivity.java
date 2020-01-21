package com.vfguille.inventoryjetpack.ui.dash;

import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.ui.base.BaseActivity;

public class DashBoardActivity extends BaseActivity {
    FloatingActionButton floatingActionButton;
    BottomAppBar bottomAppBar;
    Button btDependencies;
    Button btSecion;
    DashBoardFragment dashBoardFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dash_board_material);
//        floatingActionButton = findViewById(R.id.fabSection);
//        bottomAppBar =  findViewById(R.id.babSection);
//        btDependencies = findViewById(R.id.btnDependencies);
//        btSecion = findViewById(R.id.btnSections);
        //setSupportActionBar(bottomAppBar);

       /* btDependencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoardActivity.this, DependencyActivity.class));
            }
        });

        btSecion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardActivity.this, SectionActivity.class));
            }
        });*/

        FragmentManager fragmentManager = getSupportFragmentManager();
        dashBoardFragment = (DashBoardFragment) fragmentManager.findFragmentByTag(DashBoardFragment.TAG);
        if (dashBoardFragment == null) {
            dashBoardFragment = (DashBoardFragment) DashBoardFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.content, dashBoardFragment).commit();
        }
    }
}