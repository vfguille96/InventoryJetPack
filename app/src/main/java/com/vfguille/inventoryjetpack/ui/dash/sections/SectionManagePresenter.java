package com.vfguille.inventoryjetpack.ui.dash.sections;

import androidx.fragment.app.Fragment;

import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Section;
import com.vfguille.inventoryjetpack.data.repository.SectionRepository;

public class SectionManagePresenter extends Fragment implements SectionManageContract.Presenter {

    private SectionManageContract.View view;

    public SectionManagePresenter(SectionManageContract.View view) {
        this.view = view;
    }

    @Override
    public void validateSection(Section section) {
        view.onSuccessValidate();
    }

    @Override
    public void add(Section section) {
        if (SectionRepository.getInstance().add(section))
            view.onSuccess();
        else
            view.showError(R.string.err_add_dependency);
    }

    @Override
    public void edit(Section section) {

        if (SectionRepository.getInstance().edit(section))
            view.onSuccess();
        else
            view.showError(R.string.err_edit_dependency);
    }

    @Override
    public void delete(Section section) {
        if (SectionRepository.getInstance().delete(section))
            view.onSuccess();
        else
            view.showError(R.string.err_delete_dependency);
    }
}