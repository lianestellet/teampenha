package br.com.teampenha.quizsocial.presenter;

import br.com.teampenha.quizsocial.mvp.MvpMainPresenter;
import br.com.teampenha.quizsocial.mvp.MvpMainView;

public class MainPresenter implements MvpMainPresenter {

    private MvpMainView mMvpView;

    public MainPresenter(MvpMainView mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void initMainScreen() {
        final int lastPoints = getLastPoints();
        mMvpView.showLastPoints(lastPoints);
        mMvpView.enableNextButton();
        mMvpView.hideProgressBar();
    }

    private int getLastPoints() {
        return 0;
    }

    @Override
    public void startNewQuiz() {
        mMvpView.showProgressBar();
        mMvpView.disableNextButton();
    }
}
