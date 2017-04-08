package br.com.teampenha.quizsocial.mvp;

public interface MvpMainView {
    void showLastPoints(int points);
    void showProgressBar();
    void hideProgressBar();
    void enableNextButton();
    void disableNextButton();
    void showNextActivity();
    void logOffFacebook();
}
