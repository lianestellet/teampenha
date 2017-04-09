package br.com.teampenha.quizsocial.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.teampenha.quizsocial.model.AlternativeQuestion;
import br.com.teampenha.quizsocial.model.Question;
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
        mMvpView.startNewQuiz(loadQuestions());
    }

    // TODO Carregar as questões do json ou firebase
    private ArrayList<Question> loadQuestions() {

        int totalNumber = 5;
        List<AlternativeQuestion> alternativeQuestionsWoman = generateWomanAlternatives(totalNumber);
        List<AlternativeQuestion> alternativeQuestionsGeneral = generateGeneralAlternatives(totalNumber);

        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < totalNumber; i++) {
            AlternativeQuestion one = alternativeQuestionsWoman.get(i);
            AlternativeQuestion two = alternativeQuestionsGeneral.get(i);

            questions.add(new Question(one, two));
        }

        return questions;
    }

    private List<AlternativeQuestion> generateWomanAlternatives(int totalNumber) {

        List<AlternativeQuestion> alternatives = new ArrayList<>();

        alternatives.add(new AlternativeQuestion("Relatos de agressão", 179, AlternativeQuestion.Category.NUMERIC));
        alternatives.add(new AlternativeQuestion("Homicídios femininos por dia em 2013", 13, AlternativeQuestion.Category.NUMERIC));
        alternatives.add(new AlternativeQuestion("Mulheres vítimas de violência dando entrada no SUS", 4, AlternativeQuestion.Category.FREQUENCY));
        alternatives.add(new AlternativeQuestion("Espancamento de mulheres", 2.5, AlternativeQuestion.Category.FREQUENCY));
        alternatives.add(new AlternativeQuestion("Violência doméstica mulheres", 17, AlternativeQuestion.Category.FREQUENCY));

        return alternatives;
    }

    private List<AlternativeQuestion> generateGeneralAlternatives(int totalNumber) {
        List<AlternativeQuestion> alternatives = new ArrayList<>();

        alternatives.add(new AlternativeQuestion("Alternative 1", 30, AlternativeQuestion.Category.NUMERIC));
        alternatives.add(new AlternativeQuestion("Alternative 2", 180, AlternativeQuestion.Category.NUMERIC));
        alternatives.add(new AlternativeQuestion("Alternative 3", 10, AlternativeQuestion.Category.FREQUENCY));
        alternatives.add(new AlternativeQuestion("Alternative 4", 8, AlternativeQuestion.Category.FREQUENCY));
        alternatives.add(new AlternativeQuestion("Alternative 5", 1, AlternativeQuestion.Category.FREQUENCY));

        return alternatives;
    }


}
