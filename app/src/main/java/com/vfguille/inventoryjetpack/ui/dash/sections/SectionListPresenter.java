package com.vfguille.inventoryjetpack.ui.dash.sections;

import android.os.AsyncTask;


import com.vfguille.inventoryjetpack.R;
import com.vfguille.inventoryjetpack.data.model.Section;
import com.vfguille.inventoryjetpack.data.repository.SectionRepository;

import java.util.List;

public class SectionListPresenter implements SectionListContract.Presenter{
    private SectionListContract.View view;

    public SectionListPresenter(SectionListContract.View view) {
        this.view = view;
    }

    @Override
    public void delete(Section section) {
        if (SectionRepository.getInstance().delete(section)) {
            if (SectionRepository.getInstance().getList().isEmpty())
                view.showImageNoData();
            view.onSuccessDeleted();
        }
    }

    @Override
    public void load() {
        new AsyncTask<Void, Void, List<Section>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                setDoneImageFab(R.drawable.ic_add_black_24dp);
                checkImageNoDataIsVisible();
                view.showProgress();
            }

            @Override
            protected void onPostExecute(List<Section> sectionList) {
                super.onPostExecute(sectionList);
                view.hideProgress();
                if (sectionList.isEmpty())
                    view.showImageNoData();
                else {
                    checkImageNoDataIsVisible();
                    view.showData(sectionList);
                }
            }

            @Override
            protected List<Section> doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return SectionRepository.getInstance().getList();
            }
        }.execute();
    }

    @Override
    public void undo(Section section) {
        if (SectionRepository.getInstance().add(section)){
            view.onSuccessUndo(section);
            if (SectionRepository.getInstance().getList().size() == 1)
                view.hideImageNoData();
        }
    }

    private void checkImageNoDataIsVisible() {
        if (!view.isVisibleImgNoData())
            view.hideImageNoData();
    }

    private void setDoneImageFab(int resource){
        view.setDoneImageFab(resource);
    }
}
