package br.com.teampenha.quizsocial.mvp;

public interface MvpQuizPresenter {
    void startQuiz();
    void isCorrectAnswer(int alternative);
    void nextQuestion();
    void cancelQuiz();
    String getTotalPoints();
}
