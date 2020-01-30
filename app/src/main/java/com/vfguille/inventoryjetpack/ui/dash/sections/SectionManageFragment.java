package com.vfguille.inventoryjetpack.ui.dash.sections;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Dependency;
import com.vfguille.inventoryjetpack.data.model.Section;
import com.vfguille.inventoryjetpack.ui.base.BaseActivity;

import java.util.List;


public class SectionManageFragment extends Fragment implements SectionManageContract.View {

    public static final String TAG = "sectionManageFragment";
    private EditText edShortName;
    private EditText edName;
    private EditText edDescription;
    private FloatingActionButton floatingActionButton;
    private Spinner spRepository;
    private SectionManageContract.Presenter sectionManagePresenter;
    private List<String> getAllShortNames;

    // Métodos del contrato SectionManageContract
    @Override
    public void setPresenter(SectionManageContract.Presenter presenter) {
        this.sectionManagePresenter = presenter;
    }

    @Override
    public void showError(String error) {
        Snackbar.make(floatingActionButton, error, Snackbar.LENGTH_LONG)
                .setTextColor(getContext().getColor(R.color.colorPrimary))
                .setAnchorView(floatingActionButton)
                .show();
    }

    @Override
    public void onSuccess() {
        getActivity().onBackPressed();
    }

    @Override
    public void showError(int errAddSection) {
        Snackbar.make(floatingActionButton, errAddSection, Snackbar.LENGTH_LONG)
                .setTextColor(getContext().getColor(R.color.colorPrimary))
                .setAnchorView(floatingActionButton)
                .show();
    }


    @Override
    public void onSuccessValidate() {
        Section section = getSection();
        if (getArguments() != null)
            sectionManagePresenter.edit(section);
        else
            sectionManagePresenter.add(section);
    }
    // ---


    public static Fragment onNewInstance(Bundle bundle) {
        SectionManageFragment fragment = new SectionManageFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getAllShortNames = sectionManagePresenter.getAllShorNames();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViewElements(view);
        floatingActionButton = getActivity().findViewById(R.id.fabSection);
        floatingActionButton.setImageResource(R.drawable.ic_done_black_24dp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSectionValid())
                    sectionManagePresenter.validateSection(getSection());
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null)
            setSectionInView(bundle);
    }

    private void initializeViewElements(@NonNull View view) {
        edDescription = view.findViewById(R.id.edDescription);
        edName = view.findViewById(R.id.edName);
        edShortName = view.findViewById(R.id.edShortName);
        spRepository = view.findViewById(R.id.spInventory);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getAllShortNames);
        spRepository.setAdapter(arrayAdapter);
    }

    private void setSectionInView(Bundle bundle) {
        Section section = bundle.getParcelable(Section.TAG);
        edShortName.setEnabled(false);
        edShortName.setText(section.getShortName());
        edName.setText(section.getName());
        spRepository.setSelection(getAllShortNames.indexOf(section.getDependency()));
        edDescription.setText(section.getDescription());
    }


    private Section getSection() {
        Section section = new Section();
        section.setName(edName.getText().toString());
        section.setShortName(edShortName.getText().toString());
        section.setDependency(spRepository.getSelectedItem().toString());
        section.setDescription(edDescription.getText().toString());
        section.setImage("");
        return section;
    }

    /**
     * Comprueba las reglas de negocio del Modelo Dependency
     *
     * @return
     */
    private boolean isSectionValid() {
        // RN1: campos no vacíos
        if (TextUtils.isEmpty(edShortName.getText().toString())) {
            showError(getString(R.string.errShortNameEmpty));
            return false;
        }

        if (TextUtils.isEmpty(edName.getText().toString())) {
            showError(getString(R.string.errNameEmpty));
            return false;
        }

        if (TextUtils.isEmpty(edDescription.getText().toString())) {
            showError(getString(R.string.errDescriptionEmpty));
            return false;
        }
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_section_add, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}