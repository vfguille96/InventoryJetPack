package com.vfguille.inventoryjetpack.ui.dash.sections;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Section;
import com.vfguille.inventoryjetpack.ui.base.BaseActivity;


public class SectionActivity extends BaseActivity implements SectionListFragment.OnManageSectionListener {
    private SectionListFragment sectionListFragment;
    private FloatingActionButton floatingActionButton;
    private SectionManageFragment sectionManageFragment;
    private SectionManagePresenter sectionManagePresenter;
    private SectionListPresenter sectionListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_section);
        initializeFab();
        showSectionFragment();
    }

    private void initializeFab() {
        floatingActionButton = findViewById(R.id.fabSection);
    }

    private void showSectionFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        sectionListFragment = (SectionListFragment) fragmentManager.findFragmentByTag(SectionListFragment.TAG);
        if (sectionListFragment == null) {
            sectionListFragment = (SectionListFragment) SectionListFragment.onNewInstance(null);
            //sectionListFragment.setFab(floatingActionButton);
            fragmentManager.beginTransaction().add(R.id.content, sectionListFragment, SectionListFragment.TAG).commit();
        }

        sectionListPresenter = new SectionListPresenter(sectionListFragment);
        sectionListFragment.setPresenter(sectionListPresenter);
    }

    @Override
    public void onManageSection(Section section) {
        Bundle b = null;
        sectionManageFragment = (SectionManageFragment) getSupportFragmentManager().findFragmentByTag(SectionManageFragment.TAG);
        if (sectionManageFragment == null) {
            if (section != null) {
                b = new Bundle();
                b.putParcelable(Section.TAG, section);
            }
            sectionManageFragment = (SectionManageFragment) SectionManageFragment.onNewInstance(b);
        }

        // Depués de crear la vista, se crea el Presenter (inicialización del contrato).
        sectionManagePresenter = new SectionManagePresenter(sectionManageFragment);
        sectionManageFragment.setPresenter(sectionManagePresenter);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, sectionManageFragment, SectionManageFragment.TAG)
                .addToBackStack(null)
                .commit();
    }
}