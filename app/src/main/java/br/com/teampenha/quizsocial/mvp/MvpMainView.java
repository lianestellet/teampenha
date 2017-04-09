package br.com.teampenha.quizsocial.mvp;

import java.util.ArrayList;

import br.com.teampenha.quizsocial.model.Question;

public interface MvpMainView {
    void showLastPoints(int points);
    void showProgressBar();
    void hideProgressBar();
    void enableNextButton();
    void disableNextButton();
    void startNewQuiz(ArrayList<Question> questions);
    void logOffFacebook();
}
