package com.vfguille.inventoryjetpack.ui.dash.sections;


import com.vfguille.inventoryjetpack.data.model.Section;
import com.vfguille.inventoryjetpack.ui.base.BaseView;

import java.util.List;

public interface SectionListContract {
    interface Presenter{
        void delete(Section section);
        void load();
        void undo(Section section);
    }

    interface View extends BaseView<Presenter> {
        void showProgress();
        void hideProgress();
        void hideImageNoData();
        void showImageNoData();
        void onSuccessDeleted();
        boolean isVisibleImgNoData();
        void showData(List<Section> sectionsList);
        void onSuccessUndo(Section section);
        void setDoneImageFab(int resource);
    }
}
