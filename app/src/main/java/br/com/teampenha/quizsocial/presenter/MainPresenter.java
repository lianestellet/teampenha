package br.com.teampenha.quizsocial.presenter;

import java.io.IOException;
import java.util.ArrayList;

import br.com.teampenha.quizsocial.model.Question;
import br.com.teampenha.quizsocial.model.QuestionGenerator;
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
    public void startNewQuiz(ArrayList<Question> questions) {
        mMvpView.startNewQuiz(questions);
    }

    @Override
    public ArrayList<Question> loadQuestions(String womanJson, String generalJson) throws IOException {
        QuestionGenerator generator = new QuestionGenerator(womanJson, generalJson);
        return generator.generateQuestions(5);
    }

    @Override
    public void startLoadingProcess() {
        mMvpView.showProgressBar();
        mMvpView.disableNextButton();
    }
}
