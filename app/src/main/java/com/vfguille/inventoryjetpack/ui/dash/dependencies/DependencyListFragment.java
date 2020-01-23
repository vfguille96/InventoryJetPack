package com.vfguille.inventoryjetpack.ui.dash.dependencies;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.adapter.DependencyAdapter;
import com.vfguille.inventoryjetpack.data.model.Dependency;
import com.vfguille.inventoryjetpack.ui.base.BaseDialogFragment;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DependencyListFragment extends Fragment implements DependencyListContract.View, BaseDialogFragment.OnFinishDialogListener {

    /**
     * Comunica al listener que se ha pulsado el botón insert.
     */
    interface OnManageDependencyListener {
        void onManageDependency(Dependency dependency);
    }

    private static final int REQUEST_CODE_DELETE = 300;
    public static final String TAG = "dependenciesListFragment";
    private final int SPAN_COUNT = 2;
    private RecyclerView recyclerView;
    private DependencyAdapter dependencyAdapter;

    // Objeto-Delegado que sirve de comunicación con la clase Activity.
    private DependencyAdapter.OnManageDependencyListener onManageDependencyAdapterListener;
    private OnManageDependencyListener onManageDependencyListener;
    private DependencyListContract.Presenter presenter;
    private Dependency deleted;
    private Dependency undoDeleted;

    private FloatingActionButton floatingActionButton;
    BottomAppBar bottomAppBar;
    private LottieAnimationView lottieAnimationView;
    private LottieAnimationView skele1;
    private LottieAnimationView skele2;
    private Toolbar toolbar;
    View view;

    public static Fragment onNewInstance(Bundle bundle) {
        DependencyListFragment fragment = new DependencyListFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // Se debe indicar la siguiente llamada para que llame a los métodos que crean el menú.
        setHasOptionsMenu(true);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.dependencies));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dependencies_list, container, false);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onManageDependencyListener = (OnManageDependencyListener) context;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onManageDependencyListener = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvDependency(view);
        initializeAnimations(view);
        initializeFab(view);
    }

    private void initializeAnimations(@NonNull View view) {
        lottieAnimationView = view.findViewById(R.id.ivNoData);
        skele1 = view.findViewById(R.id.ivSkele1);
        skele2 = view.findViewById(R.id.ivSkele2);
        lottieAnimationView.setVisibility(View.GONE);
    }

    private void initializeFab(@NonNull View view) {
        floatingActionButton = getActivity().findViewById(R.id.fabSection);
        //bottomAppBar = view.findViewById(R.id.babSection);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onManageDependencyListener.onManageDependency(null);
            }
        });
    }

    private void initRvDependency(@NonNull View view) {
        recyclerView = view.findViewById(R.id.rvDependencies);
        // 1- Inicializar el listener del adapter.
        initializeListenerAdapter(onManageDependencyListener);

        // 2- Crear Adapter
        dependencyAdapter = new DependencyAdapter();
        dependencyAdapter.setOnManageDependencyClickListener(onManageDependencyAdapterListener);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        // 3- Diseño del RV.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        recyclerView.setLayoutManager(gridLayoutManager);

        // 4- Vincula la vista al modelo.
        recyclerView.setAdapter(dependencyAdapter);
    }

    private void showDeleteDialog(Dependency dependency) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.delete_dependency));
        bundle.putString(BaseDialogFragment.MESSAGE, getString(R.string.delete_body) + " " + dependency.getShortName());
        BaseDialogFragment baseDialogFragment = BaseDialogFragment.newInstance(bundle);
        baseDialogFragment.setTargetFragment(DependencyListFragment.this, REQUEST_CODE_DELETE);
        baseDialogFragment.show(getFragmentManager(), BaseDialogFragment.TAG);
        deleted = dependency;
    }

    /**
     * Es llamado cuando el usuario pulsa aceptar en el cuadro de diálogo. Se elimina la dependencia almacenada.
     */
    @Override
    public void onFinishDialog() {
        dependencyDelete();
    }

    private void dependencyDelete() {
        presenter.delete(deleted);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.load();

    }

    // ------------------------------------------------------------------------------------------------
    @Override
    public void showProgress() {
        skele1.setVisibility(View.VISIBLE);
        skele1.playAnimation();
        skele2.setVisibility(View.VISIBLE);
        skele2.playAnimation();
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        skele1.setVisibility(View.GONE);
        skele1.cancelAnimation();
        skele2.setVisibility(View.GONE);
        skele2.cancelAnimation();
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageNoData() {
        lottieAnimationView.setVisibility(View.GONE);
        lottieAnimationView.cancelAnimation();
        recyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * Actualiza el adapter para mostrar los datos.
     * @param dependencyList
     */
    @Override
    public void showData(List<Dependency> dependencyList) {
        lottieAnimationView.setVisibility(View.GONE);
        dependencyAdapter.clear();
        dependencyAdapter.load(dependencyList);
    }

    @Override
    public void onSuccessUndo(Dependency dependency) {
        dependencyAdapter.add(dependency);
        dependencyAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(DependencyListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Comprueba si hay datos en el fragment.
     * @return
     */
    @Override
    public boolean isVisibleImgNoData() {
        return lottieAnimationView.getVisibility() == View.GONE;
    }

    @Override
    public void showImageNoData() {
        lottieAnimationView.setAnimation(R.raw.nodatabox);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        recyclerView.setVisibility(View.GONE);
    }

    /**
     * Se ejecuta cuando se ha eliminado un dato en el Repository
     */
    @Override
    public void onSuccessDeleted() {
        dependencyAdapter.delete(deleted);
        dependencyAdapter.notifyDataSetChanged();
        undoDeleted = deleted;
        deleted = null;

        showSnackbarDeleted();
    }

    private void showSnackbarDeleted() {
        Snackbar.make(floatingActionButton, getString(R.string.action_delete) + " " + undoDeleted.getShortName(), Snackbar.LENGTH_LONG)
                .setAnchorView(floatingActionButton).setAction(getString(R.string.undo), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undoDeleted(undoDeleted);
            }
        }).setActionTextColor(getResources().getColor(R.color.colorPrimary, null)).show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dependency_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_order_name:
                Toast.makeText(getActivity(), getString(R.string.order), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void undoDeleted(Dependency dependency) {
        presenter.undo(dependency);
    }

    //-------------------------------------------------------------------------------------------------

    /**
     * Método que inicializa el listener que escucha los eventos del Adapter.
     */
    private void initializeListenerAdapter(final OnManageDependencyListener onManageDependencyListener) {
        onManageDependencyAdapterListener = new DependencyAdapter.OnManageDependencyListener() {

            /**
             * Se ha pulsado sobre un elemento de la lista y hay que mostrar el fragment SectionManageFragment. Se llama al método de la Activity
             * para mostrar el fragment.
             * @param dependency
             */
            @Override
            public void onEditDependency(Dependency dependency) {
                onManageDependencyListener.onManageDependency(dependency);
            }

            /**
             * Se ha pulsado largo sobre un elemento de la lista y hay que borrar el elemento. Pide confirmación de borrado.
             * @param dependency
             */
            @Override
            public void onDeleteDependency(final Dependency dependency) {
                showDeleteDialog(dependency);
            }
        };
    }
}