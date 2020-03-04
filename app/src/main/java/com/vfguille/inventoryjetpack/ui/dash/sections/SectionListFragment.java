package com.vfguille.inventoryjetpack.ui.dash.sections;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.adapter.SectionAdapter;
import com.vfguille.inventoryjetpack.data.model.Section;
import com.vfguille.inventoryjetpack.ui.base.BaseDialogFragment;


import java.util.List;

public class SectionListFragment extends Fragment implements SectionListContract.View, BaseDialogFragment.OnFinishDialogListener {
    interface OnManageSectionListener {
        void onManageSection(Section section);
    }

    public static final String TAG = "sectionListFragment";
    private static final int REQUEST_CODE_DELETE = 300;
    private final int SPAN_COUNT = 2;
    private RecyclerView recyclerView;
    private SectionAdapter sectionAdapter;
    private FloatingActionButton floatingActionButton;
    private LottieAnimationView lottieAnimationView;
    private LottieAnimationView skele1;
    private LottieAnimationView skele2;

    private SectionAdapter.OnManageSectionListener onManageSectionAdapterListener;
    private OnManageSectionListener onManageSectionListener;
    private SectionListContract.Presenter presenter;
    private Section deleted;
    private Section undoDeleted;
    private Toolbar toolbar;


    public static Fragment onNewInstance(Bundle bundle) {
        SectionListFragment fragment = new SectionListFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.sections));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRvSection(view);
        initializeAnimations(view);
        floatingActionButton = getActivity().findViewById(R.id.fabSection);
        floatingActionButton.setImageResource(R.drawable.ic_add_white_24dp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onManageSectionListener.onManageSection(null);
            }
        });
    }

    private void initRvSection(View view) {
        recyclerView = view.findViewById(R.id.rvSection);

        initializeListenerAdapter(onManageSectionListener);

        sectionAdapter = new SectionAdapter();
        sectionAdapter.setOnManageSectionClickListener(onManageSectionAdapterListener);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(sectionAdapter);
    }

    private void initializeAnimations(@NonNull View view) {
        lottieAnimationView = view.findViewById(R.id.ivNoData);
        skele1 = view.findViewById(R.id.ivSkele1);
        skele2 = view.findViewById(R.id.ivSkele2);
        lottieAnimationView.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onManageSectionListener = (OnManageSectionListener) context;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onManageSectionListener = null;
    }

    private void showDeleteDialog(Section section) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.delete_section));
        bundle.putString(BaseDialogFragment.MESSAGE, getString(R.string.delete_body_section) + " " + section.getShortName());
        BaseDialogFragment baseDialogFragment = BaseDialogFragment.newInstance(bundle);
        baseDialogFragment.setTargetFragment(SectionListFragment.this, REQUEST_CODE_DELETE);
        baseDialogFragment.show(getFragmentManager(), BaseDialogFragment.TAG);
        deleted = section;
    }

    /**
     * Es llamado cuando el usuario pulsa aceptar en el cuadro de diálogo. Se elimina la dependencia almacenada.
     */
    @Override
    public void onFinishDialog() {
        sectionDelete();
    }

    private void sectionDelete() {
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

    @Override
    public void showData(List<Section> sectionList) {
        lottieAnimationView.setVisibility(View.GONE);
        sectionAdapter.clear();
        sectionAdapter.load(sectionList);
        sectionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessUndo(Section section) {
        sectionAdapter.add(section);
        sectionAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDoneImageFab(int resource) {
        //floatingActionButton.setImageResource(resource);
    }

    @Override
    public void setPresenter(SectionListContract.Presenter presenter) {
        this.presenter = presenter;
    }

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

    @Override
    public void onSuccessDeleted() {
        sectionAdapter.delete(deleted);
        sectionAdapter.notifyDataSetChanged();
        undoDeleted = deleted;
        deleted = null;

        showSnackbarDeleted();
        presenter.load();
    }

    private void showSnackbarDeleted() {
        Snackbar.make(getView(), getString(R.string.action_delete) + " " + undoDeleted.getShortName(), Snackbar.LENGTH_LONG)
                .setAnchorView(floatingActionButton).setAction(getString(R.string.undo), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undoDeleted(undoDeleted);
            }
        }).setActionTextColor(getResources().getColor(R.color.colorPrimary, null)).show();
    }

    private void undoDeleted(Section section) {
        presenter.undo(section);
    }

    private void initializeListenerAdapter(final OnManageSectionListener onManageSectionListener) {
        onManageSectionAdapterListener = new SectionAdapter.OnManageSectionListener() {

            @Override
            public void onEditSection(Section section) {
                onManageSectionListener.onManageSection(section);
            }

            @Override
            public void onDeleteSection(final Section section) {
                showDeleteDialog(section);
            }
        };
    }

}