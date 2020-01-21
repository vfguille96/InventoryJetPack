package com.vfguille.inventoryjetpack.ui.dash.dependencies;


import com.vfguille.inventoryjetpack.data.model.Dependency;
import com.vfguille.inventoryjetpack.ui.base.BaseView;

import java.util.List;

public interface DependencyListContract {
    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void hideImageNoData();
        void showImageNoData();
        void onSuccessDeleted();
        boolean isVisibleImgNoData();
        void showData(List<Dependency> dependencyList);
        void onSuccessUndo(Dependency dependency);
    }

    interface Presenter{
        void delete(Dependency dependency);
        void load();
        void undo(Dependency dependency);
    }
}