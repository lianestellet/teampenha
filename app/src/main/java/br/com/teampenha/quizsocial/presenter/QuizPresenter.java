package br.com.teampenha.quizsocial.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.teampenha.quizsocial.model.AlternativeQuestion;
import br.com.teampenha.quizsocial.model.GreaterFactor;
import br.com.teampenha.quizsocial.model.Question;
import br.com.teampenha.quizsocial.model.QuestionFactor;
import br.com.teampenha.quizsocial.mvp.MvpQuizPresenter;
import br.com.teampenha.quizsocial.mvp.MvpQuizView;

public class QuizPresenter implements MvpQuizPresenter {

    private MvpQuizView mMvpView;
    private List<Question> mQuestions;

    private int mCurrentQuestionIndex;
    private int mRemainingLives;

    public QuizPresenter(MvpQuizView view, List<Question> questions) {
        mMvpView = view;
        mQuestions = new ArrayList<>();

        if (questions == null) {
            throw new NullPointerException("Questions could not be null");
        }

        if (questions.size() == 0) {
            throw new IllegalArgumentException("Don`t have any question");
        }

        mQuestions.addAll(questions);
        mCurrentQuestionIndex = 0;
        mRemainingLives = 3;
    }

    @Override
    public void startQuiz() {
        setNextQuestion();
    }

    @Override
    public void isCorrectAnswer(int alternative) {
        Question currentQuestion = mQuestions.get(mCurrentQuestionIndex);
        AlternativeQuestion chosenAlternative = currentQuestion.getAlternativeOne();

        if (alternative == 2) {
            chosenAlternative = currentQuestion.getAlternativeTwo();
        }

        QuestionFactor factor = currentQuestion.getQuestionFactor();
        boolean isCorrect = factor.correctAlternative().equals(chosenAlternative);

        if (!isCorrect) {
            mRemainingLives--;
        }

        if (mRemainingLives == 0) {
            mMvpView.showGameOver();

        } else {
            mMvpView.updateRemaingLives(mRemainingLives);
            mMvpView.animateAnswer();
        }

    }

    @Override
    public void nextQuestion() {
        mCurrentQuestionIndex = mCurrentQuestionIndex + 1;
        if (mCurrentQuestionIndex < mQuestions.size()) {
            setNextQuestion();
        } else {
            finishQuiz();
        }
    }


    private void setNextQuestion() {
        Question question = mQuestions.get(mCurrentQuestionIndex);
        mMvpView.setAlternativeOne( question.getAlternativeOne() );
        mMvpView.setAlternativeTwo( question.getAlternativeTwo() );

        QuestionFactor factor = question.getQuestionFactor();

        if (factor instanceof GreaterFactor) {
            mMvpView.setGreatherQuestionTitle();
        } else {
            mMvpView.setLessQuestionTitle();
        }
    }

    private void finishQuiz() {
        mMvpView.finishQuiz();
    }

    @Override
    public void cancelQuiz() {

    }
}
