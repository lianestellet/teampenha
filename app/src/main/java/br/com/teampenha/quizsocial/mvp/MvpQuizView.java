package br.com.teampenha.quizsocial.mvp;

import br.com.teampenha.quizsocial.model.AlternativeQuestion;

public interface MvpQuizView {
    void setAlternativeOne(AlternativeQuestion alternative);
    void setAlternativeTwo(AlternativeQuestion alternative);
    void setGreatherQuestionTitle();
    void setLessQuestionTitle();
    void animateAnswer();
    void finishQuiz();

    void showGameOver();
    void updateRemaingLives(int lives);
}
