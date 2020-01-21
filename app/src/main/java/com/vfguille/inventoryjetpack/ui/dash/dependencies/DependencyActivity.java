package com.vfguille.inventoryjetpack.ui.dash.dependencies;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Dependency;
import com.vfguille.inventoryjetpack.ui.base.BaseActivity;

public class DependencyActivity extends BaseActivity implements DependencyListFragment.OnManageDependencyListener{

    private DependencyListFragment dependencyListFragment;
    private DependencyManageFragment dependencyManageFragment;
    private DependencyManagePresenter dependencyManagePresenter;
    private DependencyListPresenter dependencyListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showListFragment();
    }

    /**
     * Muestra el DependenciesFragment
     */
    private void showListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyListFragment = (DependencyListFragment) fragmentManager.findFragmentByTag(DependencyListFragment.TAG);
        if (dependencyListFragment == null) {
            dependencyListFragment = (DependencyListFragment) DependencyListFragment.onNewInstance(null);
            fragmentManager.beginTransaction().add(R.id.content, dependencyListFragment, DependencyListFragment.TAG).commit();
        }

        // Depués de crear la vista, se crea el Presenter (inicialización del contrato).
        dependencyListPresenter = new DependencyListPresenter(dependencyListFragment);
        dependencyListFragment.setPresenter(dependencyListPresenter);
    }
/*
    private void showAddFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyManageFragment = (SectionManageFragment) fragmentManager.findFragmentByTag(SectionManageFragment.TAG);
        if (dependencyManageFragment == null)
            dependencyManageFragment = (SectionManageFragment) SectionManageFragment.onNewInstance(null);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, dependencyManageFragment, SectionManageFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }*/

    @Override
    public void onManageDependency(Dependency dependency) {
        Bundle b = null;
        dependencyManageFragment = (DependencyManageFragment) getSupportFragmentManager().findFragmentByTag(DependencyManageFragment.TAG);
        if (dependencyManageFragment == null) {
            if (dependency != null) {
                b = new Bundle();
                b.putParcelable(Dependency.TAG, dependency);
            }
            dependencyManageFragment = (DependencyManageFragment) DependencyManageFragment.onNewInstance(b);
        }

        // Depués de crear la vista, se crea el Presenter (inicialización del contrato).
        dependencyManagePresenter = new DependencyManagePresenter(dependencyManageFragment);
        dependencyManageFragment.setPresenter(dependencyManagePresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, dependencyManageFragment, DependencyManageFragment.TAG)
                .addToBackStack(null)
                .commit();
    }
}