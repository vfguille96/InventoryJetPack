package com.vfguille.inventoryjetpack.ui.dash.sections;

import com.vfguille.inventoryjetpack.data.model.Section;
import com.vfguille.inventoryjetpack.ui.base.BaseView;

import java.util.List;

public interface SectionManageContract {

    interface View extends BaseView<Presenter> {
        void onSuccess();
        void showError(int errAddSection);
        void onSuccessValidate();
        void showError(String error);
    }

    interface Presenter {
        void validateSection(Section section);

        void add(Section section);

        void edit(Section section);

        void delete(Section section);

        List<String> getAllShorNames();
    }
}