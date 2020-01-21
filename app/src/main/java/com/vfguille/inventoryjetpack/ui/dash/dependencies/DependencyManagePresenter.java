package com.vfguille.inventoryjetpack.ui.dash.dependencies;

import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Dependency;
import com.vfguille.inventoryjetpack.data.repository.DependencyRepository;

public class DependencyManagePresenter implements DependencyManageContract.Presenter {

    private DependencyManageContract.View view;

    public DependencyManagePresenter(DependencyManageContract.View view) {
        this.view = view;
    }

    /**
     * Valida RN2,RN3, RN4.
     *
     * @param dependency
     */
    @Override
    public void validateDependency(Dependency dependency) {
        //view.showError();
        view.onSuccessValidate();
    }

    @Override
    public void add(Dependency dependency) {
        if (DependencyRepository.getInstance().add(dependency))
            view.onSuccess();
        else
            view.showError(R.string.err_add_dependency);
    }

    @Override
    public void edit(Dependency dependency) {

        if (DependencyRepository.getInstance().edit(dependency))
            view.onSuccess();
        else
            view.showError(R.string.err_edit_dependency);
    }

    @Override
    public void delete(Dependency dependency) {
        if (DependencyRepository.getInstance().delete(dependency))
            view.onSuccess();
        else
            view.showError(R.string.err_delete_dependency);
    }
}