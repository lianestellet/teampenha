package br.com.teampenha.quizsocial.mvp;

import java.io.IOException;
import java.util.ArrayList;

import br.com.teampenha.quizsocial.model.Question;

public interface MvpMainPresenter {
    void initMainScreen();
    void startNewQuiz(ArrayList<Question> questions);
    ArrayList<Question> loadQuestions(String womanData, String generalData) throws IOException;
    void startLoadingProcess();
}
